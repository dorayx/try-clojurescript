#!/bin/bash

LEIN_CMD="$PWD/bin/lein"

if [ ! -e $LEIN_CMD ]; then
  echo 'error : lein not installed'
  exit
fi

if [ -z $1 ] || [ ! -d $1 ]; then
  echo "the directory is not found : $1"
  exit
fi

PROJECT_NAME=${1:-'sample_project'}
PROJECT_NAME=${PROJECT_NAME//-/_}
LEIN_TEMPLATE=${2:-'figwheel'}

cd $PROJECT_NAME && $LEIN_CMD $LEIN_TEMPLATE