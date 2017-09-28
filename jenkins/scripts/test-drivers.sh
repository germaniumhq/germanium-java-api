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

cd /home/ciplogic/project/germanium-tests

# the environment comes from the Jenkinsfile
mvn clean install


