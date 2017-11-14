#!/bin/bash
echo "compiling exceptions..."
javac src/exceptions/*.java
echo "compiling source classes (used during incrememntal testing)..."
javac -cp ".:./src/" ./src/model/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Board tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Correction tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling EmuladorFiveGuess tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java

