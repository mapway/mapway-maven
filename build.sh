#!/bin/bash

echo " > deploy package to remote repository. <"
echo " >================================<"

version=$1
profile=$2

function print_usage()
{
    echo  example : build 1.0.4-SNAPSHOT release
}


if [ -z ${version} ]; then
   print_usage
   exit
fi

if [ -z ${profile} ]; then
    print_usage
    exit
fi

echo release ${version} to ${profile}

mvn versions:set -DnewVersion=${version}
mvn versions:commit
mvn clean deploy -P${profile}
