#!/bin/bash

echo "install jar to local repository."

version=$1

echo ${version}

if [ -z ${version} ]; then
  echo  usage : install.sh version
  echo  example : install 1.0.4-SNAPSHOT
  exit
fi

mvn versions:set -DnewVersion=${version}
mvn versions:commit
mvn clean install -Pto-ziroom
