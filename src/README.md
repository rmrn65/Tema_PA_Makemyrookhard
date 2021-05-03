# Bot Sah Umakemyrookhard

## Etapa 1 - mișcarea unui singur pion

*INSTRUCTIUNI DE COMPILARE*

0. Rulare: Se va da comanda: xboard -fcp "make run" -debug

*DETALII DESPRE STRUCTURA PROIECTULUI. ALGORITMICĂ.*
1. Conectarea la xboard
	În clasa Main se ruleaza programul principal. Am creat un obiect de tipul
	scanner cu ajutorul căruia vom prelua comenzi de la xboard. Comenzile vor 
	fi analizate pe rând într-o buclă while, și în funcție de aplicabilitatea
	acestora, sunt tratate într-un if (în cazul mișcărilor pieselor) sau în
	switch (pentru restul comenzilor). Botul trimite răspunsul calculat în
	output.

2. Crearea tablei interne (clasa Board)
	Fiecare piesă este reprezentată de o clasă separată care moștenește clasa
	Piece. În clasa board se creează o matrice de obiecte Piece care reprezintă
	tabla internă a botului, și care va reține mișcările făcute în xboard.
	În clasa Board, metoda move primește coordonatele unei mișcări sub formă de
	string și modifică matricea de obiecte ca atare.

3. Mișcarea pionului (clasa Pawn)
	Pionul care se va mișca va fi cel de pe coloana "e". Acesta are atribute
	care rețin poziția curentă și culoarea. Pionul reține un atribut x care e 
	egal cu 1 sau -1, pentru a-i modifica poziția în funcție de culoarea sa.
	Clasa pawn implementează metode care verifică în ce parte se poate mișca 
	pionul, și metode care execută mișcarea. Aceste metode actualizează poziția
	curentă a pionului, execută mișcarea pe tabla internă și returnează un
	string cu mutarea care urmează să fie dat xboardului drept comandă. 

4. Flaguri
	În main folosesc flagurile first_move, start, go și quit. Acestea pot fi
	0 sau 1 ,și se schimbă în funcție de comenzile primite de la xboard după
	cum urmează: - start = 1 indică dacă botul calculează sau nu mișcări;
				 - first-move = 1 indică faptul că pionul se poate mișca
				 cu două căsuțe în față;
				 - go = 1 indică ieșirea din starea "Edit game";
				 - quit = 1 indică ieșirea din program;
**COMENZI :**

5. Comanda new
	Comanda new resetează tabla internă a botului și activează flagurile de
	start și first-move.

6. Comanda force
	Comanda force dezactivează flagurile start si first-move, astfel că botul
	nu mai calculează mișcări, însă clasa Board continuă să le primească
	și să modifice tabla internă.

7. Comanda go
	Comanda go activeaza flagul de start si go ,astfel că botul începe iar
	să calculeze mișcări.

8. Comanda white
	Comanda white setează botul să joace cu piesele albe, iar pionul cu care va
	muta va fi cel care inițial era pe coloana "e".

9. Comanda black
	Comanda black setează botul să joace cu piesele negre,iar pionul cu care va
	muta va fi cel care inițial era pe coloana "e".

10. Comanda quit
	Comanda quit va activa flagul quit care va ieși din bucla while și programul 
	nu va mai primi comenzi.

*RESPONSABILITATEA FIECĂRUI MEMBRU AL ECHIPEI*
	În această etapă toți membrii echipei au lucrat împreună la același cod,
	procentajul de participare fiind egal.


## Etapa 2 - Implementarea tuturor miscarilor legale + tratarea sahului + rocadele

*INSTRUCTIUNI DE COMPILARE*

0. Rulare: In aceasta etapa se poate utiliza comanda:
	xboard -fcp "make run" -scp "make run" -debug (+ in xboard CTRL+T pentru a atribui 
	ambelor culori bot-ul nostru)

*STRUCTURA PROIECTULUI*

1. Clasele pentru piese
	In aceasta etapa am implementat o clasa abstracta Piece, parinte al tuturor pieselor,
	in acesta am declarat doua metode abstracte: canMove(Board) si move(String), metode 
	folosite in clasa Main, cand se muta o piesa, pentru a oferi o interfata predictibila
	si uniformizata, indiferent de tipul piesei care este mutata.

2. Update clasa Pawn
	- Am implementat fata de etapa trecuta en Passant, verificand urmatoarele constrangeri:
		I. Verific daca sunt pe linia 5 cu pion alb, respectiv linia 4 cu pion negru
		II. Verific daca am la stanga sau la dreapta mea un pion advers si daca in spatele
		acestuia este liber
		III. Cu ajutorul unei variabile lastMove, care se updateaza in Board.move la fiecare
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


3. Randomizare mutarilor + piesei care va muta
	La fiecare mutare tinem cont de ce piese poate muta bot-ul, alegem random una din acele piese,
	apoi alegem o mutare random dintre cele legale ale piesei respective. Pentru acest lucru folosim
	clasa Random, si metoda Random.nextInt(). Pentru fiecare piesa am implementat o lista de miscari
	legale, pe care o verificam la momentul mutarii.

4. Clasa Bishop
	La implementarea nebunului, verific cele 2 diagonale pe care poate muta nebunul, adaug miscari
	la cele posibile atat timp cat:
		I. Nu ies din bound-urile tablei
		II. Nu dau de piesa de culoarea nebunului
		III. Daca dau de piesa inamica, ultima mutare pe diagonala respectiva este capturarea acesteia

*RESPONSABILITATEA FIECĂRUI MEMBRU AL ECHIPEI*

Implementarea pieselor:
	Queen - Robert
	Rook - Alin
	Knight - Alex
	Bishop - Stefan

Implementarea regelui + tratarea sahului + rocade: Robert si Alex
Implementarea En Passant + promovare pion: Stefan si Alin

Bugfixing + Randomizare mutari + Stabilire structura claselor: Toti



