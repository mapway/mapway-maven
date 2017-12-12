@echo off
set version=%1

if not defined version (
  echo  usage : build.bat version 
  echo  example : build 1.0.4-SNAPSHOT
  goto exit
  )

:build
mvn versions:set -DnewVersion=%version% && mvn versions:commit && mvn clean deploy -P to-enncloud

:exit