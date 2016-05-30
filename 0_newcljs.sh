#!/bin/bash

if [ -z $1 ]; then
	echo 'error : the project name is undefined'
	exit
fi

DEFAULT_PROJECT_NAME='sample_project'
DEFAULT_NAMESPACE='sample-project'
PROJECT_NAME=${1//-/_}
NAMESPACE=${PROJECT_NAME//_/-}

cp -r $DEFAULT_PROJECT_NAME $PROJECT_NAME && cd $PROJECT_NAME
mv src/$DEFAULT_PROJECT_NAME src/$PROJECT_NAME
find . -type f -exec sed -i "s/$DEFAULT_NAMESPACE/$NAMESPACE/g" {} +