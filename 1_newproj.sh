#!/bin/bash

LEIN_CMD="$PWD/bin/lein"
PROJECT_NAME=$1
LEIN_TEMPLATE=${2:-'figwheel'}

if [ ! -e $LEIN_CMD ]; then
  echo 'error : lein not installed'
  exit
fi

if [ -z $PROJECT_NAME ]; then
  echo 'error : the project name is undefined'
  exit
fi

if [ -d $PROJECT_NAME ]; then
  echo "error : the project exists : $1"
  exit
fi

$LEIN_CMD new $LEIN_TEMPLATE $PROJECT_NAME
cd $PROJECT_NAME && $LEIN_CMD $LEIN_TEMPLATE