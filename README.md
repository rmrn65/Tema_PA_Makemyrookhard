## Etapa 2 - Implementarea tuturor miscarilor legale + tratarea sahului + rocadele

*INSTRUCTIUNI DE COMPILARE*

0. Rulare: In aceasta etapa se poate utiliza comanda:
	xboard -fcp "make run" -scp "make run" -debug (+ in xboard CTRL+T pentru a atribui 
	ambelor culori bot-ul nostru)

*STRUCTURA PROIECTULUI*

0. Clasa Piece
	Clasa Piece e o clasă abstractă care este moștenită de toate piesele de pe
	tablă. Aceasta conține metoda move, care mută piesa după ce alege la întâmplare
	dintr-un vector de mișcări posibile, și metoda canMove, care indică dacă
	piesa are vreo mutare posibilă.

1. Regină - clasa Queen
	Este împărțită în 3 metode de tip canMove ( pentru mișcarea pe coloana , pe linie
	și pe diagonală), în care se adaugă posibilele pătrățele pe care se poate pune.
	Se verifică pe orizontală și pe verticală și pe diagonală care sunt pătrățelele
	libere, până se lovește de o piesă de orice culoare. Dacă piesa e de culoare
	adversă, se adaugă posibilitatea de a o captura. Această parcurgere se efectueză
	cu while-uri care parcurg coloanele liniile si diagonalele matricei de obiecte.

2. Nebun - clasa Bishop
	Asemenea ca la regină, doar că se elimina verificarea pe orizontală și verticală

3. Tură - clasa Rook
	Asemenea ca la regină, doar ca se elimină verificarea pe verticală

4. Cal - clasa Night
	Clasa Night contine clasa interna NightMoves 
	definita cu 2 indici linie si coloana reprezentand offsetii necesari
	calului de a ajunge din pozitia curenta intr-o pozitie ulterioara
	pe tabla. In metoda canMove se face o filtrare a acestora, adica
	in vectorul de posibile miscari se elimina cele invalide (cele
	care ies din tabla sau cele care sunt ocupate de piese de aceeiasi
	culoare). In metoda move se alege din vector o pereche de offseti random, 
	se actualizeaza noile coordonate ale calului (linie + coloana)
	cat si noua pozitie si se efectueaza miscarea.

5. Update clasa Pawn
	- Am implementat fata de etapa trecuta en Passant, verificand urmatoarele constrangeri:
		a. Verific daca sunt pe linia 5 cu pion alb, respectiv linia 4 cu pion negru
		b. Verific daca am la stanga sau la dreapta mea un pion advers si daca in spatele
		acestuia este liber
		c. Cu ajutorul unei variabile lastMove, care se updateaza in Board.move la fiecare
		mutare, verific daca ultima mutare a fost a pionului pe care vreau sa il capturez
		en Passant, de 2 casute (din pozitie initiala)
	Daca se indeplinesc aceste cerinte, adaug mutarea la lista de mutari posibile, si verific
	daca a fost selectata in Board.move, daca mutarea curenta e enPassant, exista o metoda in
	Board, moveEnPassant, care pe langa modificarea uzuala din move, imi sterge si piesa din
	spatele square-ului destinatie (pionul capturat)

	- Am implementat pawn promotion: Daca destinatia unui pion e ultima linie (8 pentru alb,
	1 pentru negru), imi aleg random la ce piesa voi face promovare (prin utilizarea unui
	String "qrbn" -> care va fi flag-ul trimis catre xboard la promovare (q -> Queen, r -> Rook
	b -> Bishop, n -> Knight). Cu ajutorul acestui ultim caracter, in momentul mutarii, modific
	referinta din square-ul respectiv de la pion la piesa la care am promovat, elimanand pionul
	din setul de piese, adaugand piesa promovata.

6. Rege - clasa King
	a. Verificarea daca regele este în șah, sau dacă o mutare e validă (isInCheck) :
		Pentru aceasta se folosește o metodă care primește o poziție dată ca 
		parametru, și verifică dacă o altă piesă atacă poziția respectivă astfel:
		Se verifică pe diagonală dacă se află o regină sau un nebun de culoare
		adversă , se verifică pe linie și coloană dacă se află o regină sau 
		o tură de culoare adversă, se verifică pe pozițiile pe care ar putea
		sări calul din coordonatele date ca parametru, dacă există un cal,
		se verifică (în funcție de culoare) dacă pe linia următoare pe diagonală
		se află un pion advers, și se verifică dacă în jur e regele de culaore
		opusă. Se returnează o listă nulă de poziții dacă poziția respectivă 
		nu se află în șah, altfel se returnează coordonatele care pun poziția
		în șah. Luăm în considerare daca sunt mai multe poziții care pun 
		acea pătrățică în șah deoarece în cazul în care regele primește
		șah de la doua piese simultan, acesta nu poate fi apărat, și e
		forțat să se miște. Folosim metodat isInCheck pentru a verifica
		dacă poziția regelui e în șah, sau dacă poziția pe care ar putea
		regele să se miște este în șah.
	b. Metodă care întoarce mișcările ce pot apăra regele (defendKing) :
		Verificarea este similară metodei isInCheck, doar că aceasta caută
		piese de aceeași culoare pe traiectoriile menționate mai sus. Dacă
		se găsește una, se va adăuga în vectorul returnat, mișcarea piesei
		pe pătrățica ce va apăra regele. 
	c. Metodă care informează o piesă dacă mișcarea pe care o va face poate
	pune regele în șah (canIMove):
		Metoda returnează fals sau adevărat dacă schimbarea poziției unei 
		piese va pune regele în șah.
		Se tratează două cazuri: dacă regele nu se află în șah, iar mutarea
		piesei va pune regele în șah, și dacă regele se află în șah , iar
		mutarea piesei(în încercarea de a apăra regele), va descoperi un alt
		șah. În primul caz, se returnează fals, și se va șterge mai tarziu 
		mișcarea piesei, în al doilea caz, piesa care în mod normal ar trebui
		să apere regele va rămâne pe loc, și va pierde mișcarea care ar încerca
		să apere regele. 
	d. Metodă care întoarce o mișcare aleatorie ce va apăra regele într-un șah
		(canDefendKing):
		Metoda apelează defendKing pentru fiecare pătrățică care poate fi ocupată
		cu scopul de a apăra regele de șah.
		Mișcările generate de defendKing sunt salvate într-un vector de mișcări
		optionsToDefend care se resetează la fiecare verificare. La final, se alege
		o mișcare aleatorie dintre cele date, după ce se elimină mișcările pieselor
		care deja apără regele de o altă piesă ce atacă.
	e. Asemenea calului, regele are o clasă internă KingMoves care păstrează 
		posibilele mișcări pe care le poate face, acestea fiind generate în
		movesForKing. Mișările din movesForKing nu sunt filtrate, ci doar ipotetice.
	f. Rocada - (canCastleShort & canCastleLong) verifică dacă regele poate efectua
		rocada. Acesta verifică dacă este prima mutare a turei sau a regelui , reținută
		într-un flag din clasă, ”first_move”,și dacă toate pătrățelel dintre rege și
		tură sunt deja ocupate de altă piesa, sau dacă sunt atacate, folosind isInCheck.
	g. În canMove se filtrează mutările generate de movesForKing, și se șterg cele care
		nu verifică metoda isInCheck sau cele care ar ocupa o poziție din afara tablei sau
		populată de altă piesă de aceeași culoare.

*MAIN - MODIFICĂRI*
	Main - In cadrul acestei parti am facut urmatoarele modificari:
		Se verifica daca regele este in sah folosind functia isInCheck 
		care returneaza un array[]. Daca este diferit de null iar
		dimensiunea este de 2(pe pozitia 4 retinem dimensiunea, deci daca 
		array[4] == 2 inseamna ca ia sah doar de la o piesa), atunci regele
		apeleaza functia canKingDefend care obliga piesele sa il apere.
		Stringul rezultat retine rezultatul apelului acestei functii, iar
		in cazul in care aceasta iese cu null (nici o piesa nu a avut o pozitie
		favorabile de a-l apara), se apeleaza move pe King, deci se va muta
		regele intr-o pozitie valida. Altfel daca rezultat este diferit
		de null se muta piesa ce a ales sa apere regele. Daca regele 
		primeste sah din 2 directii array[4] == 4 inseamna ca acesta 
		trebuie obligatoriu mutat (nu are alta solutie), deci se apeleaza
		king.move. In cazul in care regele nu primeste sah se efectueaza o mutare
		randomizata, alegand o piesa random, verificand daca aceasta poate
		efectua mutari prin canmove si daca acesta se poate muta fara
		a pune regele in sah. Daca unul din cele 2 scenarii este True

*RESPONSABILITATEA FIECĂRUI MEMBRU AL ECHIPEI*

Implementarea pieselor:
	Queen - Robert
	Rook - Alin
	Knight - Alex
	Bishop - Stefan

Implementarea regelui + tratarea sahului + rocade: Robert si Alex
Implementarea En Passant + promovare pion: Stefan si Alin

Bugfixing + Randomizare mutari + Stabilire structura claselor: Toti



