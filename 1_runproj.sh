#!/bin/bash

LEIN_CMD="$PWD/bin/lein"
PROJECT_NAME=$1
LEIN_TEMPLATE=${2:-'figwheel'}

if [ ! -e $LEIN_CMD ]; then
  echo 'error : lein not installed'
  exit
fi

if [ -z $PROJECT_NAME ] || [ ! -d $PROJECT_NAME ]; then
  echo "the directory is not found : $1"
  exit
fi

cd $PROJECT_NAME && $LEIN_CMD $LEIN_TEMPLATE