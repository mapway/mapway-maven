#!/bin/bash

echo "deploy jar."

version=$1

echo ${version}

if [ -z ${version} ]; then
  echo  usage : build.sh version
  echo  example : build 1.0.4-SNAPSHOT
  exit
fi

mvn versions:set -DnewVersion=${version}
mvn versions:commit
mvn clean deploy -Pto-ziroom
