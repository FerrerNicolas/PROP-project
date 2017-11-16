#!/bin/bash
echo "This will play games with random secret codes and difficulty in a loop, terminate whenever you want"
java -cp ".:./src/:./tests" domini.EmuladorFiveGuess.EmuladorFGRandom
