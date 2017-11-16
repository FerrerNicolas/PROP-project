#!/bin/bash
java -cp ".:./src/:./tests/:./hamcrest-core-1.3.jar:./junit-4.12.jar" org.junit.runner.JUnitCore domini.GameJUNIT.MasterTestSuiteGame
