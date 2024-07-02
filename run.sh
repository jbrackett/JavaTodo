#!/bin/bash

START_DEPS_CMD="docker compose up -d --build postgres redis"
START_CMD="exec java -jar -Dserver.port=8099"
DEBUG_CMD="-Xms128m -Xmx128m -Xdebug -Xrunjdwp:transport=dt_socket,address=*:5004,server=y,suspend=y"
JAR="build/libs/JavaTodo.jar"

# load the local environment variable settings
#export $(cat local.env | xargs)
export $(grep -v '^#' local.env | xargs)

ARGS="-m"
if [[ "$1" != "" ]]; then
  ARGS=$1
fi

case $ARGS in
  -m|--minimal)
    eval $START_DEPS_CMD
    LOG_APPENDER=console $START_CMD $JAR
    ;;
  -D|--debug)
    eval $START_DEPS_CMD
    $START_CMD $DEBUG_CMD $JAR
    ;;
  -d|--docker)
    docker build -t javatodo .
    docker compose up -d
    docker compose logs -f -t javatodo
    ;;
  -h|--help)
    cat <<EOM
Usage: ./run.sh [-d|--docker] [-m|--minimal] [-h|--help]
   -m --minimal   run deps in docker but not application (default)
   -d --docker    run application in docker container
   -D --debug     run in remote JVM debug mode
EOM
    ;;
esac
