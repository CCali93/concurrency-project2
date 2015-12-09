@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;/Users/austin/Desktop/concurrency-project2.jar

%JAVA_HOME%\bin\java -Xms128m -Xmx384m -Xnoclassgc ro.my.class.MyClass