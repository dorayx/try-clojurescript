#!/bin/bash

if [ ! -d $1 ]; then
  echo "the directory is not found : $1"
  exit
fi

PROJECT_NAME=${1:-'sample_project'}

cd $PROJECT_NAME && rlwrap java -cp cljs.jar:src clojure.main repl.clj