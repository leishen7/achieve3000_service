#!/bin/bash


#prepare the parameters

if[ "$JAVA_HOME="" ]; then
      JAVA_HOME=opt/java/jdk8
fi;

CP=$CP:config
for i in lib/*.jar
do 
   CP=$CP:i
done;


JVM_ARGS="-Dapp.name=restservice \
		  -Dlogback.configurationFile=logback.xml \
	      -Dlog.dir=c:\Logs
	      
JAVA_MAIN=com.achieve.rest.main.MainApp

runcmd=$JAVA_HOME/bin/java $JVM_ARGS -classpath $CP $JAVA_MAIN

echo Running: $runcmd

exec $runcmd $*
