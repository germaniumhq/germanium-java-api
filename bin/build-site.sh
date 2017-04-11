#!/usr/bin/env bash

LOCAL_DIR=$(readlink -f $(dirname $(readlink -f "$0"))/..)
mvn -Dmaven.repo.local=$LOCAL_DIR/m2repo clean install $@

