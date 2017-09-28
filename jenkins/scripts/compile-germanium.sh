#!/usr/bin/env bash

# fail fast
set -e

#############################################################################
# Utility functions.
#############################################################################
function deactivate_proxy() {
    old_http_proxy="$http_proxy"
    old_https_proxy="$https_proxy"
    old_ftp_proxy="$ftp_proxy"
    unset http_proxy
    unset https_proxy
    unset ftp_proxy
}

function activate_proxy() {
    export http_proxy="$old_http_proxy"
    export https_proxy="$old_https_proxy"
    export ftp_proxy="$old_ftp_proxy"
}

deactivate_proxy

# we checkout the sources of the project
echo git clone --recursive $SOURCES_URL /home/ciplogic/project
git clone --recursive $SOURCES_URL /home/ciplogic/project

echo mkdir -p /home/ciplogic/.m2
mkdir -p /home/ciplogic/.m2

echo cp /scripts/settings.xml /home/ciplogic/.m2/settings.xml
cp /scripts/settings.xml /home/ciplogic/.m2/settings.xml

cd /home/ciplogic/project

# we skip the tests, because they will be run independently
mvn clean install -DskipTests

# we run only the unit tests that are defined in Germanium,
# not the integration tests.
cd germanium
mvn test

