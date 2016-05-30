#!/bin/bash

PROJECT_NAME=$1
CLJS_JAR_PATH="$PWD/bin/cljs.jar"

if [ ! -d $PROJECT_NAME ]; then
  echo "the directory is not found : $1"
  exit
fi

cd $PROJECT_NAME && rlwrap java -cp $CLJS_JAR_PATH:src clojure.main repl.clj