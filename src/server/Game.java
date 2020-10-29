package server;

import java.io.IOException;
import java.util.*;

public class Game{
    private final Server s;
    public final static String MSG_SPLITTER = String.valueOf( ( char )29 );

    Game( Server server ){
        this.s = server;
    }

    void start() throws InterruptedException{
        s.writeLog( "Game started\n" );
        boolean insomniac = false;
        if( s.cardsInGame.contains( "Insomniac" ) ){
            insomniac = true;
            s.cardsInGame.remove( "Insomniac" );
        }
        if( s.cardsInGame.contains( "Copycat" ) ){
            try{
                makeCopycat();
            }catch( IOException e ){
                s.writeLog( "No response from copycat." );
            }
            s.cardsInGame.remove( "Copycat" );
        }
        boolean isWerewolf = false;
        for( int i = 0; i < Card.werewolvesQuant; ++i ){
            if( s.cardsInGame.contains( "Werewolf_" + i ) ){
                isWerewolf = true;
                break;
            }
        }
        if( s.cardsInGame.contains( "Mystic wolf" ) ) isWerewolf = true;
        String werewolvesMsg = "";
        if( isWerewolf ){
            try{
                werewolvesMsg = makeWerewolves();
            }catch( IOException e ){
                s.writeLog( "No response from werewolves." );
            }
            for( int i = 0; i < Card.werewolvesQuant; ++i )
                s.cardsInGame.remove( "Werewolf_" + i );
        }
        if( s.cardsInGame.contains( "Mystic wolf" ) ){
            try{
                makeMysticWolf();
            }catch( IOException e ){
                s.writeLog( "No response from mystic wolf." );
            }
            s.cardsInGame.remove( "Mystic wolf" );
        }
        if( s.cardsInGame.contains( "Minion" ) )
            makeMinion( werewolvesMsg );

        Random rand = new Random();

        // TODO main loop, here we have to put all the cards, and call theirs functions
        while( s.cardsInGame.size() > 0 ){
            String card = s.cardsInGame.get( rand.nextInt( s.cardsInGame.size() ) );
            try{
                switch( card ){
                    case "Witch": makeWitch(); break;
                    case "Beholder": makeBeholder(); break;
                    case "Seer": makeSeer(); break;
                    case "Tanner": break;
                    case "Thing": makeThing(); break;
                    case "Paranormal investigator": makeParanormal(); break;
                    case "Robber": makeRobber(); break;
                    case "Troublemaker": makeTroublemaker(); break;
                    case "Apprentice seer": makeApprenticeSeer(); break;
                }
            }catch( IOException e ){
                s.writeLog( "No response from " + card + "." );
            }
            s.cardsInGame.remove( card );
        }
        if( insomniac )
            makeInsomniac();
        s.sendGame( 0, "WakeUp" );
        s.voteButton.setDisable( false );
    }

    //new function copycat
    void makeCopycat() throws IOException, InterruptedException{
        int idOfCopycat = startRole( "Copycat" );
        if( idOfCopycat < 0 ) return;

        //receive his moves, which is generally numbers splitted with (char)29 sign, here is one number, but for example Seer will send two numbers
        String chosenCard = s.receiveGame( idOfCopycat );     // first received number
        //s.writeLog( "Player chose card " + chosenCard );
        //send name of card, which player has become
        String cardName = s.cardsInCenter[ s.getTableCardId( chosenCard ) ];
        s.realCopycat = cardName;
        s.sendGame( idOfCopycat, cardName );
        //s.writeLog( "This card is " + cardName );
        //Change his card information on server
        s.cardsOnBegin.set( s.cardsOnBegin.indexOf( "Copycat" ), cardName );

        //Remove this card from list of cards (we don't want to make copycat move again)
        s.cardsInGame.remove( "Copycat" );
        Thread.sleep( 3000 );
    }

    String makeWerewolves() throws InterruptedException, IOException{
        s.sendGame( 0, "WEREWOLF" );
        s.writeLog( "Werewolf" + "'s move" );
        StringBuilder str = new StringBuilder();
        boolean isAnyoneWerewolf = false;
        if( s.cardsOnBegin.contains( "Mystic wolf" ) ) isAnyoneWerewolf = true;
        else{
            for( int i = 0; i < Card.werewolvesQuant; ++i ){
                if( s.cardsOnBegin.contains( "Werewolf_" + i ) ){
                    isAnyoneWerewolf = true;
                    break;
                }
            }
        }
        if( !isAnyoneWerewolf ){
            //s.writeLog( "All werewolves are on table" );
            Thread.sleep( 7000 );
        }
        else{
            Vector< Integer > werewolves = new Vector<>();
            for( int i = 0; i < Card.werewolvesQuant; ++i ){
                int indexOfWerewolf = s.cardsOnBegin.indexOf( "Werewolf_" + i );
                if( indexOfWerewolf != -1 ){
                    str.append( s.players.get( indexOfWerewolf ).name ).append( MSG_SPLITTER );
                    werewolves.add( s.players.get( indexOfWerewolf ).id );
                    //s.writeLog( "Werewolf is player " + s.players.get( indexOfWerewolf ).id );
                }
            }
            int indexOfMystic = s.cardsOnBegin.indexOf( "Mystic wolf" );
            if( indexOfMystic != -1 ){
                str.append( s.players.get( indexOfMystic ).name ).append( MSG_SPLITTER );
                werewolves.add( s.players.get( indexOfMystic ).id );
                //s.writeLog( "Mystic wolf is player " + s.players.get( indexOfMystic ).id );
            }
            for( Integer werewolf: werewolves )
                s.sendGame( werewolf, str.toString() );
            // Lone werewolf
            if( werewolves.size() == 1 ){
                String cardToSee = s.receiveGame( werewolves.get( 0 ) ).split( MSG_SPLITTER )[ 0 ];
                int cardToSeeId = s.getTableCardId(cardToSee);
                s.sendGame( werewolves.get( 0 ), s.cardsInCenter[ cardToSeeId ] );
            }
            Thread.sleep( 7000 );       //Not necessary, time for werewolves to meet together
        }
        s.writeLog( "Werewolves fall asleep" );
        return str.toString();
    }

    void  makeMinion( String werewolvesMsg ) throws InterruptedException{
        int minionsId = startRole( "Minion" );
        if( minionsId < 0 ) return;
        s.sendGame( minionsId, werewolvesMsg );
        Thread.sleep( 7000 );
        s.cardsInGame.remove( "Minion" );
        s.writeLog( "Minion falls asleep" );
    }

    void makeMysticWolf() throws InterruptedException, IOException {
        int idOfMysticWolf = startRole("Mystic wolf");
        if(idOfMysticWolf<0) return;
        String cardToSee = s.receiveGame( idOfMysticWolf ).split( MSG_SPLITTER )[ 0 ];
        int cardToSeeId = s.getTableCardId(cardToSee);
        s.sendGame( idOfMysticWolf, s.cardsInCenter[cardToSeeId] );
        //s.writeLog(s.cardsInCenter[cardToSeeId]);
        Thread.sleep( 3000 );
        s.writeLog( "Mystic wolf falls asleep" );
    }

    void makeApprenticeSeer() throws InterruptedException, IOException {
        int idOfApprenticeSeer = startRole("Apprentice seer");
        if(idOfApprenticeSeer<0) return;
        String cardToSee = s.receiveGame( idOfApprenticeSeer ).split( MSG_SPLITTER )[ 0 ];
        int cardToSeeId = s.getTableCardId(cardToSee);
        s.sendGame( idOfApprenticeSeer, s.cardsInCenter[cardToSeeId] );
        //s.writeLog(s.cardsInCenter[cardToSeeId]);
        Thread.sleep( 3000 );
        s.writeLog( "Apprentice seer falls asleep" );
    }

    void makeWitch() throws InterruptedException, IOException{
        int idOfWitch = startRole( "Witch" );
        if( idOfWitch < 0 ) return;
        String chosenCard = s.receiveGame( idOfWitch );
        String cardName = s.cardsInCenter[ s.getTableCardId( chosenCard ) ];
        s.sendGame( idOfWitch, cardName );
        String chosenPlayer = s.receiveGame( idOfWitch );
        int playersId = s.players.indexOf( s.getPlayer( chosenPlayer ) );
        s.cardsInCenter[ s.getTableCardId( chosenCard ) ] = s.cardsNow.get( playersId );
        s.cardsNow.set( playersId, cardName );
        Thread.sleep( 3000 );
        s.writeLog( "Witch falls asleep" );
    }

    void makeTroublemaker() throws InterruptedException, IOException{
        int idOfTroublemaker = startRole( "Troublemaker" );
        if( idOfTroublemaker < 0 ) return;
        String[] chosenCards = s.receiveGame( idOfTroublemaker ).split( MSG_SPLITTER );
        Collections.swap( s.cardsNow, s.players.indexOf( s.getPlayer( chosenCards[ 0 ] ) ), s.players.indexOf( s.getPlayer( chosenCards[ 1 ] ) ) );
        Thread.sleep( 3000 );
        s.writeLog( "Troublemaker falls asleep" );
    }

    void makeSeer() throws InterruptedException, IOException {
        int idOfSeer = startRole("Seer");
        if(idOfSeer<0) return;
        String[] chosenCards = s.receiveGame( idOfSeer ).split( MSG_SPLITTER );
        int chosenCard1ID = s.getTableCardId(chosenCards[0]);
        int chosenCard2ID = s.getTableCardId(chosenCards[1]);
        String chosenCard1 = s.cardsInCenter[chosenCard1ID];
        String chosenCard2 = s.cardsInCenter[chosenCard2ID];
        s.sendGame(idOfSeer,chosenCard1+MSG_SPLITTER+chosenCard2);
        Thread.sleep( 3000 );
        s.writeLog( "Seer falls asleep" );
    }

    void makeBeholder() throws InterruptedException {
        int idOfBeholder = startRole("Beholder");
        if (idOfBeholder < 0) return;
        if( s.cardsOnBegin.contains( "Seer" ) ){
            String seerName = s.players.get(s.cardsOnBegin.indexOf("Seer")).name;
            s.sendGame(idOfBeholder, seerName);
        }
        else
            s.sendGame(idOfBeholder,"NoSeer");
        Thread.sleep( 3000 );
        s.writeLog( "Beholder falls asleep" );
    }
    void makeInsomniac() throws InterruptedException{
        int idOfInsomniac = startRole( "Insomniac" );
        if(idOfInsomniac<0) return;
        String insomniacNow = s.cardsNow.get(s.cardsOnBegin.indexOf("Insomniac"));
        //s.writeLog( "This card is " + insomniacNow );
        s.sendGame(idOfInsomniac, insomniacNow );
        Thread.sleep( 3000 );
        s.cardsInGame.remove( "Insomniac" );
        s.writeLog( "Insomniac falls asleep" );
    }

    void makeThing() throws InterruptedException, IOException{
        int thingId = startRole( "Thing" );
        if( thingId < 0 ){
            s.sendGame( 0, "NOTHING" );
            return;
        }
        String chosenCard = s.receiveGame( thingId );
        for( Server.Player player: s.players ){
            if( player.name.equals( chosenCard ) )
                s.sendGame( player.id, "TOUCH" );
            else
                s.sendGame( player.id, "NOTHING" );
        }
        Thread.sleep( 3000 );
        s.writeLog( "Thing falls asleep" );
    }

    void makeParanormal() throws InterruptedException, IOException{
        int paranormalId = startRole( "Paranormal investigator" );
        if( paranormalId < 0 ) return;
        for( int i = 0; i < 2; ++i ){
            String chosenCard = s.receiveGame( paranormalId );
            String card = s.cardsNow.get( s.players.indexOf( s.getPlayer( chosenCard ) ) );
            s.sendGame( paranormalId, card );
            if( card.split( "_" )[ 0 ].equals( "Tanner" ) || card.split( "_" )[ 0 ].equals( "Werewolf" ) || card.split( "_" )[ 0 ].equals( "Mystic wolf" ) ){
                s.realParanormal = card;
                break;
            }
        }
        Thread.sleep( 3000 );
        s.writeLog( "Paranormal investigator falls asleep" );
    }

    void makeRobber() throws IOException, InterruptedException{
        int robberId = startRole( "Robber" );
        if( robberId < 0 ) return;
        String chosenCard = s.receiveGame( robberId );
        String card = s.getPlayersCard( chosenCard );
        int chosenPlayerIdx = s.players.indexOf( s.getPlayer( chosenCard ) );
        int paranormalIdx = s.players.indexOf( s.getPlayer( robberId ) );
        String paranormalsCard = s.cardsNow.get( paranormalIdx );
        s.cardsNow.set( paranormalIdx, card );
        s.cardsNow.set( chosenPlayerIdx, paranormalsCard );
        s.sendGame( robberId, card );
        Thread.sleep( 3000 );
        s.writeLog( "Robber falls asleep" );
    }

    //function does same begin of every role and returns id of player with this role, if role was not on the middle
    int startRole( String card ) throws InterruptedException{
        s.sendGame( 0, card.toUpperCase() );
        s.writeLog( card + "'s move" );
        if( !s.cardsOnBegin.contains( card ) ){
            //s.writeLog( card + " is on table" );
            Thread.sleep( new Random().nextInt( 10000 ) + 10000 );
            s.cardsInGame.remove( card );
            s.writeLog( card + " falls asleep" );
            return -1;
        }
        else{
            //s.writeLog( card + " is player " + idOfCard );
            return s.players.get( s.cardsOnBegin.indexOf( card ) ).id;
        }
    }
}
