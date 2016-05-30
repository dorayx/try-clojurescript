#!/bin/bash

if [ -z $1 ]; then
	echo 'error : the project name is undefined'
	exit
fi

DEFAULT_PROJECT_NAME='sample-project'
DEFAULT_NAMESPACE='sample-project'
DEFAULT_COMPILED_CORE_DIR='sample_project'
PROJECT_NAME=${1//_/-}
NAMESPACE=PROJECT_NAME
COMPILED_CORE_DIR=${PROJECT_NAME//-/_}

cp -r $DEFAULT_PROJECT_NAME $PROJECT_NAME && cd $PROJECT_NAME
mv src/$DEFAULT_COMPILED_CORE_DIR src/$COMPILED_CORE_DIR
find . -type f -exec sed -i "s/$DEFAULT_NAMESPACE/$NAMESPACE/g" {} +