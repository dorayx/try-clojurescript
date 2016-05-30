#!/bin/bash

if [ ! -d 'bin' ]; then
  mkdir bin
fi

curl -o ./bin/lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
chmod +x ./bin/lein
