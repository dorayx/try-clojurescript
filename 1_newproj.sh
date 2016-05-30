#!/bin/bash

LEIN_CMD="$PWD/bin/lein"

if [ ! -e $LEIN_CMD ]; then
  echo 'error : lein not installed'
  exit
fi

if [ -z $1 ]; then
  echo 'error : the project name is undefined'
  exit
fi

if [ -d $1 ]; then
  echo "error : the project exists : $1"
  exit
fi

PROJECT_NAME=${1:-'sample_project'}
PROJECT_NAME=${PROJECT_NAME//-/_}
LEIN_TEMPLATE=${2:-'figwheel'}

$LEIN_CMD new $LEIN_TEMPLATE $PROJECT_NAME
cd $PROJECT_NAME && $LEIN_CMD $LEIN_TEMPLATE