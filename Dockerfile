#This docker file is to pack automation testing code into an image
FROM openjdk:8u212-jre-alpine3.9
# Curl is to get data from website
# jq is cmdline json parser
RUN apk add curl jq
WORKDIR /code

# From this point, everthing will happen in /code dir
# libs contains all dependencies
COPY ./target/libs libs

# testNG-suites has all testng xml files
COPY ./target/testNG-suites testNG-suites

# src and test jars
COPY ./target/selenium-docker.jar selenium-docker.jar
COPY ./target/selenium-docker-tests.jar selenium-docker-tests.jar

# other files for data driven testing
COPY ./src/test/test-resources src/test/test-resources

# HealthCheck To run tests only after nodes starts & gets registered to hub
# When transferring sh/bash file from windows to linux, format issue araise, to overcome use notepad++ & edit->eolversion->unix & save as .bash & use dos2unix
COPY healthcheck.sh healthcheck.sh
RUN dos2unix healthcheck.bash

ENTRYPOINT sh healthcheck.sh