#!/bin/bash
MyPID=$!                        # Record PID
echo $MyPID                     # Print to terminal
java -jar concurrency-project2.jar
kill $MyPID                     # kill PID
