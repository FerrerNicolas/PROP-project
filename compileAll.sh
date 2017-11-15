#!/bin/bash
echo "compiling exceptions..."
javac src/exceptions/*.java
echo "compiling source classes (used during incremental testing)..."
javac -cp ".:./src/" ./src/model/*.java
echo "compiling Ai tests..."
javac -cp ".:./src/" ./tests/domini/Ai/*.java
echo "compiling Board tests..."
javac -cp ".:./src/" ./tests/domini/Board/*.java
echo "compiling Correction tests..."
javac -cp ".:./src/" ./tests/domini/Correction/*.java
echo "compiling EmuladorFiveGuess tests..."
javac -cp ".:./src/" ./tests/domini/EmuladorFiveGuess/*.java
echo "compiling EmuladorGenetic tests..."
javac -cp ".:./src/" ./tests/domini/EmuladorGenetic/*.java
echo "compiling Game tests..."
javac -cp ".:./src/" ./tests/domini/Game/*.java
echo "compiling GlobalRecords tests..."
javac -cp ".:./src/" ./tests/domini/GlobalRecords/*.java
echo "compiling Player tests..."
javac -cp ".:./src/" ./tests/domini/Player/*.java
echo "compiling Ranking tests..."
javac -cp ".:./src/" ./tests/domini/Ranking/*.java
echo "compiling Tuple tests..."
javac -cp ".:./src/" ./tests/domini/Tuple/*.java
echo "compiling User tests..."
javac -cp ".:./src/" ./tests/domini/User/*.java
