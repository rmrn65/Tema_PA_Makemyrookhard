JFLAGS = -g
JC = javac
JVM= java
FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    Main.java \
    Piece.java \
    Rook.java \
    Bishop.java \
    Night.java \
    Pawn.java \
    Queen.java \
    Board.java \
    King.java 

MAIN = Main

build: classes

classes: $(CLASSES:.java=.class)

run: 
	java Main
clean:
	$(RM) *.class
