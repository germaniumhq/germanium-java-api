#!/bin/bash

set -e

GERMANIUM_JAVA=$(readlink -f $(dirname $(readlink -f "$0"))/../)
GERMANIUM_PYTHON=$(readlink -f $(dirname $(readlink -f "$0"))/../../germanium/)

mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/impl/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/locators/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/points/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/util/

cp -R $GERMANIUM_PYTHON/germanium/impl/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/impl/

cp -R $GERMANIUM_PYTHON/germanium/locators/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/locators/

cp -R $GERMANIUM_PYTHON/germanium/points/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/points/

cp -R $GERMANIUM_PYTHON/germanium/util/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/util/

