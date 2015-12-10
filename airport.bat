@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;/Users/austin/Desktop/concurrency-project2.jar

%JAVA_HOME%\bin\java -jar -Xms128m -Xmx384m -Xnoclassgc concurrency-project2.jar