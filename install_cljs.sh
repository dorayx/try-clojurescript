#!/bin/bash

if [ ! -d 'bin' ]; then
  mkdir bin
fi

curl -o ./bin/cljs.jar https://github.com/clojure/clojurescript/releases/download/r1.8.51/cljs.jar