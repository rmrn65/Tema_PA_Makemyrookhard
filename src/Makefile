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
    Knight.java \
    Pawn.java \
    Queen.java \
    King.java 

MAIN = Main

build: classes

classes: $(CLASSES:.java=.class)

run: 
	java Main.java
clean:
	$(RM) *.class
