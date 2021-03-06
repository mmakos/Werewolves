# Werewolves

![Android screen](http://www.mmakos.pl/storage/2021/02/androidPhone-1536x743.png)
![Desktop screen](http://www.mmakos.pl/storage/2021/02/winlaptop-1536x1178.png)

## This documentation refers to version 1.x and 2.x
If you want to be up-to-date with this project, please visit [my website](http://www.mmakos.pl/programming/werewolves). There you can find descriptions of the newest releases etc.

### ([Werewolves](#werewolves)) [Wilkołaki](#wilkołaki)
1. ([About game](#about-game)) [Opis gry](#opis-gry)
2. ([Installation](#installation)) [Instalacja](#instalacja)
3. ([Start-up](#start-up)) [Uruchomienie](#uruchomienie)
    * ([Client](#client-player)) [Klient](#klient-gracz)
    * ([Server](#server-game-admin)) [Serwer](#serwer-administrator-gry)
4. ([Game](#game)) [Rozgrywka](#rozgrywka)
5. ([Problems](#problems)) [Błędy](#błędy)
    * ([Common problems](#common-problems)) [Częste problemy](#częste-problemy)
    * ([Errors during the game](#errors-during-the-game)) [Błędy podczas gry](#błędy-podczas-gry)

## About game
**Werewolves** is multiplayer game similar to "Mafia", but each player has its own unique role and whole action happens during one night. That's why it's also called "One Night Ultimate". This application is online adaptation of that game.
You can find precise description and game rules on [One Night Ultimate](http://onenightultimate.com/) website.

## Installation
You can download Windows installer or zipped game folder from [*releases* tab](https://github.com/mmakos/Werewolves/releases). Then you have to install the game or unpack *.zip* file. You have to have Java JRE installed on your computer as well. You can get it from here: <https://java.com/pl/download>.<br>
If you have Windows system on your computer it is highly recommended to download windows installer instead of zipped folder because strange things did happen to some when running this version - yet never to ne, so I wasn't able to find solution. However if you are forced to get zipped folder and you experience strange behaviour of this app check [*problems section*](#problems).

## Start-up

### Client (player)
To join the game you have to run *Werewolves.exe*. In *ip* field you have to type server's ip which you want to join to (it should be given by game admin). In *port* field you have to type *23000*. After clicking *login* button you will be asked to wait until game admin starts game.

### Server (game admin)
Things are getting a little bit complicated here. I don't share any server so in order to organise game you will have to set up your own server. If you have ever done this in Minecraft you probably know that you can use one of free VPNs like Hamachi. Best option however is to login into your router by typing in your web browser your router's ip, which will be your IP (you can check it on site like [this](https://www.myip.com/)) or your local IP with *.1* end, which you can check by typing in *cmd* (command line) *ipconfig* command. So if your local ip is *192.168.100.9* you have to type in your browser *192.168.100.**1*** - it's default gateway address. Then you have to set up port forwarding on your router. This step will be different for every router but the common and most important thing is to set up port to forward to value *23000* and ip address to our computer address (in our case it is *192.168.100.9*), as shown below. And the best option of all is to search for some tutorials on YouTube.<br><br>
Then you have to run *SerWerewolves.exe* and click *Run Server* button. When all players will join (you see theirs names on the screen) click *Start game* button, choose the cards and start game. Before clicking *Start game* you can kick sb out of server by typing his name in the field that pops up after clicking *Kick sb out* button.

![Server instruction](https://lh3.googleusercontent.com/fife/ABSRlIoENTgQX3nmef-2z-g4rvtSfd1RXM6gb59A_8QcYkkE-DjHtih8teSC-TzmEApKg689wyxtInuwMUPCfK6a8LzXwt_EKq2RzjEFdbsRP-KH-iqyliEVM70zr8hndDKz-oYXTr3ajcrDKveG1YLvZwdd0JlTh3nCHOqYZHM1EmVVlY289Di0fdyjI3p1AQGqFCPFyILbw4wAf1rhFAWPjjQo6YN_Q1Cpgr53ahAZOLrJaj1CiOhFXY3K_yPzuEBReFKDSoAmqbNoa9EA4HbhxaHohd4yuIOO-kyROnurJLrm7f6xqI32IicixtYHz-tS796ZBofM5rn8UkGhcMCrRY3AiOMHwBZYQkexYUkxe-qY2u6J5Wl2ClBFLDUpfDxW4dr-F-nuqUzGvN7dvg_-1iZ8W2F2BNGNPP-_m1kCs4pqzqmuinV00cnTtPuZuKc537889eeIaj_y5yYFqKctHie-N0i6coUb29TEgt8cJo28GnESaRTIi-Kp7tnp2SakCTHp2XjgrwN8dzE-CvfAouoeK5VKBI8jUqoRGR6WtcoTrXN_CrCAvb1umkGhPig79oiiHBOeuQ1Ty6lznLUsWkVLuJ6p2Wsk4HPIl5uwhyrsWd15gFgvRhL-qWQM53UXhP3Y5GpU1FPKLTPMyTPAJAqtNqoTc8mMrIoNoyQYWcKTlSIrkngYznA8eH0_I0KjopEI6k3L7mh43kAK0ExLSx9_spjeEMEMBg=w1600-h828-ft)

## Game
In the night wait for your turn and do what's written on the screen. When the night ends connect with players with meeting app and find out who is guilty. Then admin will click *Vote* button and you can select player you want to kill or one of middle cards if you don't want to kill anyone.

## Problems
I don't have possibility to test this game on multiple platforms, especially macOS systems. So the game may not work properly on some computers. 

### Common problems
* Installer doesn't run:
    * Try to disable your antivirus - it can block this application due to the lack of certificate.
    * Make sure that you're not trying to run *.exe* file on non-windows system.
    * Download ZIP file instead *(highly unrecommended solution)*
* Installed application *.exe* doesn'trun:
    * Try to disable your antivirus - it can block this application due to the lack of certificate.
    * Check if your computer have Java JRE installed. If not, install it.
    * If you have different version of JRE than 1.8.x try to install this version [from here](https://java.com/pl/download/). You can check your JRE version by typing in cmd (command line): `java --version`.
    * Check if path to your java folder exists in system variable *PATH*. Go to *Edit system environment variables* ortype in cmd cmd `echo %PATH%`. If it's not there, you have to add it manually.
* Jar file from *.zip* doesn't run:
    * As above
    * Try to run with option *Open with...*, find folder with java installed and choose *java.exe* file.
    * Try to run from cmd by typing: `java -jre Werewolves.jar`.
* You cannot connect to server:
    * Check if you have correct IP and port typed.
    * Run app as administrator.
    * Check if firewall doesn't block this app or just disable it for a moment.
* Instead of game instructions and information you see strange *null* word:
    * Run app as administrator.
    * Check game folder for existence of folder *languages*, files *polish.txt* i *english.txt* inside it and whether this files are not empty. If not, reinstall game.
    
*To run cmd, best click `Shift + PPM` somewhere in your game folder (but not on the file) and select "open PowerShell here" or "Open comand line here"*.

### Errors during the game
In case of errors like: "When mystic wolf dies,werewolves wins", so in case of game logic issues write to me what is the issue or [create issue here on git](https://github.com/mmakos/Werewolves/issues) and describe your problem.
<br>However if game presents some unexpected behaviour, e.g. someone doesn't have vote chance (that happens on some computers and I don't have possibility to find the cause) please follow those steps:
* Run app from cmd. If you have *.exe* file type in cmd `./Werewolves.exe` and if you have *.jar* file, then type `java -jar Werewolves.jar`.
* Try to get this error as before. You should see some strange error messages in cmd (it's called stacktrace) - copy it and send it to me or create a git issue.

# Wilkołaki

## Opis gry
Wilkołaki to wieloosobowa gra podobna do popularnej "Mafii", z tym że każdy ma swoją własną rolę, a cała akcja dzieje się podczas jednej doby. Niniejszy projekt jest przełożeniem tejże gry na wersję online.
Dokładny opis gry oraz zasady znajdziesz na stronie [One Night Ultimate](http://onenightultimate.com/).

## Instalacja
Instalator na Windows'a lub spakowane pliki gry (jeśli mamy Mac'a albo Linux'a) można pobrać z [zakładki *releases*](https://github.com/mmakos/Werewolves/releases). Następnie należy zainstalować grę podążając za wskazówkami instalatora. Dodatkowo konieczne jest posiadanie JRE (Java Runtine Environment) w wersji 8 - można je pobrać z <https://java.com/pl/download>.<br>
Gdy mamy na komputerze system Windows to zalecam pobranie instalatora, gdyż z tym zipem częściej dzieją się dziwne rzeczy, które na moich komputerach nigdy się nie działy, więc nie bardzo mogę znaleźć ich przyczynę. Ale jeśli wystąpi jakić błąd to super - wtedy patrz punkt [*Błędy*](#błędy).

## Uruchomienie

### Klient (gracz)
Aby dołączyć do gry jako klient, należy uruchomić aplikację **Werewolves.exe**. W pole *ip* należy wpisać adres IP serwera do którego chcemy dołączyć (podany przez administratora gry). W pole *port* należy wpisać numer portu, na którym serwer udostępnia usługę, czyli domyślnie **23000**. Po wciśnięciu przycisku *login* poczekaj na rozpoczęcie gry przez administratora. 

### Serwer (administrator gry)
Tu sprawa jest bardziej skomplikowana. Ja nie udostępniam żadnego serwera, więc aby zorganizować grę, należy na swoim komputerze postawić serwer. Jeśli stawialiscie kiedyś serwery w Minecrafcie to można skorzystać z jakichś darmowych VPN-ów typu Hamachi. Najlepiej jednak po prostu zalogować się do routera, wpisując w przeglądarkę adres routera, czyli swoje IP lub lokalny adres IP z końcówką *1*, czyli albo sprawdzamy swoje IP np. na stronie <https://www.moje-ip.eu/> i wpisujemy go jako adres internetowy w przeglądarce albo sprawdzamy lokalny adres komputera wpisując w *cmd* (command line) plecenie *ipconfig* i jeżeli adres komputera jest np. *192.168.100.11* to wpisujemy w przeglądarce *192.168.100.**1*** - adres braby domyślnej. Następnie należy na routerze ustawić przekierowanie portu (będzie to pocja *Port Forwarding Rules*, *forwarding* lub coś z serwerem). Na kżdym routerze wugląda to inaczej ale ważne żeby porty usawić na *23000* a adres IP na nasze IP (jak na poniższym screenie). A w ogóle najlepiej to znaleźć jakiś tutorial na YT jak postawić serwer na routerze.<br><br>
Następnie należy uruchomić aplikację *SerWerewolves.exe* oraz wcisnąć *Run Server*. Kiedy połączy się zadowalająca nas liczba graczy (gracze pojawiają nam się w okienku), wciskamy *Start game*, wybieramy karty i rozpoczynamy grę.

![Server instruction](https://lh3.googleusercontent.com/fife/ABSRlIoENTgQX3nmef-2z-g4rvtSfd1RXM6gb59A_8QcYkkE-DjHtih8teSC-TzmEApKg689wyxtInuwMUPCfK6a8LzXwt_EKq2RzjEFdbsRP-KH-iqyliEVM70zr8hndDKz-oYXTr3ajcrDKveG1YLvZwdd0JlTh3nCHOqYZHM1EmVVlY289Di0fdyjI3p1AQGqFCPFyILbw4wAf1rhFAWPjjQo6YN_Q1Cpgr53ahAZOLrJaj1CiOhFXY3K_yPzuEBReFKDSoAmqbNoa9EA4HbhxaHohd4yuIOO-kyROnurJLrm7f6xqI32IicixtYHz-tS796ZBofM5rn8UkGhcMCrRY3AiOMHwBZYQkexYUkxe-qY2u6J5Wl2ClBFLDUpfDxW4dr-F-nuqUzGvN7dvg_-1iZ8W2F2BNGNPP-_m1kCs4pqzqmuinV00cnTtPuZuKc537889eeIaj_y5yYFqKctHie-N0i6coUb29TEgt8cJo28GnESaRTIi-Kp7tnp2SakCTHp2XjgrwN8dzE-CvfAouoeK5VKBI8jUqoRGR6WtcoTrXN_CrCAvb1umkGhPig79oiiHBOeuQ1Ty6lznLUsWkVLuJ6p2Wsk4HPIl5uwhyrsWd15gFgvRhL-qWQM53UXhP3Y5GpU1FPKLTPMyTPAJAqtNqoTc8mMrIoNoyQYWcKTlSIrkngYznA8eH0_I0KjopEI6k3L7mh43kAK0ExLSx9_spjeEMEMBg=w1600-h828-ft)

## Rozgrywka
W trakcie nocy czekaj na swoją kolej oraz wykonuj polecenia. Kiedy noc się skończy w celu ustalenia kto jest kim, trzeba połączyć się z graczami przez jakiegoś zooma czy coś. Następnie administrator włącza głosowanie i możemy głosować.

## Błędy
Niestety nie mam możliwości przetestować gry na zróżnicowanych platformach, szczególnie na urządzeniach z systemem macOS. W związku z tym aplikacja może nie działać poprawnie na niektórych urządzeniach.<br>

### Częste problemy
* Instalator nie chce się uruchomić:
    * Spróbuj wyłączyć antywirusa - aplikacja nie posiada certyfikatu, więc programy antywirusowe mogą blokować tę aplikację.
    * Sprawdź, czy twój system na pewno obsługuje pliki *.exe*, czyli czy jest to Windows.
    * Pobierz wersję ZIP *(niezalecane rozwiązanie)*
* Zainstalowana aplikacja *.exe* nie chce się uruchomić:
    * Spróbuj wyłączyć antywirusa - aplikacja nie posiada certyfikatu, więc programy antywirusowe mogą blokować tę aplikację.
    * Sprawdź czy posiadasz na komputerze Java JRE (Java Runtime Environment). Jeśli nie, to zainstaluj.
    * Jeśli masz inną wersję JRE niż 1.8.x to spróbuj zainstalować tą wersję [stąd](https://java.com/pl/download/). Wersję JRE możesz sprawdzić wpisując w cmd (command line): `java --version`.
    * Sprawdź, czy w zmiennej systemowej *PATH* znajduje się ścieżka do JRE. W tym celu wyszukaj w menu start *Edytuj zmienne środowiskowe systemu* lub wpisz w cmd `echo %PATH%`. Jeśli nie ma tam ścieżki do twojego JRE to musisz ją dodać.
* Aplikacja z *.zip* nie chce się uruchomić:
    * jw.
    * spróbuj uruchomić aplikację przez opcję *otwórz za pomocą* i znajdź folder z zainstalowanym JRE i wybierz aplikację *java.exe*.
    * spróbuj uruchomić aplikację wpisując w cmd: `java -jre Werewolves.jar`.
* Nie możesz połączyć się z serwerem:
    * Sprawdź czy podałeś dobre IP oraz port.
    * Uruchom grę jako administrator.
    * Sprawdź czy firewall nie blokuje połączenia lub po prostu o na chwilę wyłącz.
* Gra zamiast napisówwyświetla dziwne *null*:
    * Uruchom grę jako administrator.
    * Sprawdź czy w folderze gdzie zainstalowałeś grę znajduje się folder *languages* a w nim pliki *polish.txt* i *english.txt* oraz czy te pliki nie są puste. Jeśli nie to zaistaluj grę jeszcze raz.
    
*Aby uruchomić cmd najlepiej klikniej gdzieś w folderze gry `Shift + PPM` (ale nie na plik) i wybierz opcję "otwórz tutaj okno programu PowerShell" lub "otwórz wiersz polecenia tutaj" na starszej wersji Windowsa niż Windows 10.*

### Błędy podczas gry
W przypadku błędów typu: "Kiedy Mystic Wolf zginie to wilkołaki wygrywają", czyli błędów w logice gry napisz do mnie lub stwórz [issue tutaj na Git](https://github.com/mmakos/Werewolves/issues) i opisz problem.
<br>Kiedy jednak jest to błąd, który powoduje nietypowe zachowanie gry, np. komuś nie aktywowało się głosowanie (to występuje u niektórych i jeszcze nie wiem dlaczego) to wykonaj następujące kroki:
* Uruchom grę z cmd. Jeśli masz plik *.exe* to wpisz w cmd `./Werewolves.exe`, a jeśli masz plik *.jar* to wpisz `java -jar Werewolves.jar`.
* Spróbuj doprowadzić do tego błędu. Gra powinna wypisać w cmd jakieś błędy (tzw. stacktrace) - skopiuj je i mi wyślij bądź stwórz git issue.


