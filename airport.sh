#!/bin/bash
MyPID=$!                        # Record PID
echo $MyPID                     # Print to terminal
java -jar /Users/austin/Desktop/concurrency-project2.jar
kill $MyPID                     # kill PID
