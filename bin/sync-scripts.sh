#!/bin/bash

set -e

GERMANIUM_JAVA=$(readlink -f $(dirname $(readlink -f "$0"))/../)
GERMANIUM_PYTHON=$(readlink -f $(dirname $(readlink -f "$0"))/../../germanium/)

mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/impl/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/locators/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/points/
mkdir -p $GERMANIUM_JAVA/src/main/resources/germanium/util/

mkdir -p $GERMANIUM_JAVA/src/main/resources/com/germaniumhq/germanium/

cp -R $GERMANIUM_PYTHON/germanium/impl/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/impl/

cp -R $GERMANIUM_PYTHON/germanium/locators/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/locators/

cp -R $GERMANIUM_PYTHON/germanium/points/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/points/

cp -R $GERMANIUM_PYTHON/germanium/util/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/util/

cp $GERMANIUM_PYTHON/germanium/germanium-extensions-loaded.js \
   $GERMANIUM_JAVA/src/main/resources/com/germaniumhq/germanium/germanium-extensions-loaded.js

cp $GERMANIUM_PYTHON/germanium/germanium-ie8-getComputedStyle.js \
   $GERMANIUM_JAVA/src/main/resources/com/germaniumhq/germanium/germanium-ie8-getComputedStyle.js
