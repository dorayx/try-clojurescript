#!/bin/bash

PROJECT_NAME=$1

if [ ! -d $PROJECT_NAME ]; then
  echo "the directory is not found : $1"
  exit
fi

cd $PROJECT_NAME && rlwrap java -cp cljs.jar:src clojure.main repl.clj