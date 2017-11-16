#!/bin/bash
echo "This will play every single possible secret code on every possible difficulty and output some stats about the games at the end."
echo "Be advised this will take a very long time to finish and current progress is not reported."
echo "executing..."
java -cp ".:./src/:./tests" domini.EmuladorFiveGuess.EmulatorFiveGuessAll
