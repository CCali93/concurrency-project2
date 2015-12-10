#!/bin/bash
MyPID=$!                        # Record PID
echo $MyPID                     # Print to terminal
CLASSPATH=.
CLASSPATH=$CLASSPATH:concurrency-project2.jar
java cf concurrency-project2.jar src/*.java
java -jar -Xms128m -Xmx384m -Xnoclassgc concurrency-project2.jar
kill $MyPID                     # kill PID
