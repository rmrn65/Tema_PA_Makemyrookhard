# Bot Sah Umakemyrookhard

## Etapa 1 - mișcarea unui singur pion

0. Rulare: În folderul src/ se va da comanda: xboard -fcp "make run" -debug

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


