@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;concurrency-project2.jar

%JAVA_HOME%\bin\java cf concurrency-project2.jar src/*.java
%JAVA_HOME%\bin\java -jar -Xms128m -Xmx384m -Xnoclassgc concurrency-project2.jar