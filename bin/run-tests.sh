#!/usr/bin/env bash

# change to the project folder.
cd $(readlink -f $(dirname $(readlink -f "$0"))/..)


if [[ "$1" == "firefox" ]]; then
    export TEST_BROWSER="firefox:http://192.168.0.6:4444/wd/hub"
    export TEST_HOST="192.168.0.23:8000"
    mvn test -Dcucumber.options="-t ~@nofirefox"
elif [[ "$1" == "chrome" ]]; then
    export TEST_BROWSER="chrome:http://192.168.0.6:4444/wd/hub"
    export TEST_HOST="192.168.0.23:8001"
    mvn test -Dcucumber.options="-t ~@nochrome"
elif [[ "$1" == "ie" ]]; then
    export TEST_BROWSER="ie:http://192.168.0.6:4444/wd/hub"
    export TEST_HOST="192.168.0.23:8002"
    mvn test -Dcucumber.options="-t ~@noie"
elif [[ "$1" == "edge" ]]; then
    export TEST_BROWSER="edge:http://192.168.0.6:4444/wd/hub"
    export TEST_HOST="192.168.0.23:8003"
    mvn test -Dcucumber.options="-t ~@noedge"
fi

