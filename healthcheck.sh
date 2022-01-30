#!/usr/bin/env bash
# Environment Variables
# HOST
# BROWSER
# MODULE
echo "Checking if hub is ready - $HOST"
while [ "$( curl -s http://$HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
sleep 1
done
echo $pwd
# start the java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=$HOST -DBROWSER=$BROWSER org.testng.TestNG testNG-suites/$MODULE