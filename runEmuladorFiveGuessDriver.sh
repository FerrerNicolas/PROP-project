#!/bin/bash
echo "When inputing a secret code: 0 is a blank, 1-6 are colors."
java -cp ".:./src/:./tests" domini.EmuladorFiveGuess.EmuladorFiveGuess
