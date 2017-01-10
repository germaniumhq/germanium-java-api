#!/usr/bin/env bash

# change to the project folder.
cd $(readlink -f $(dirname $(readlink -f "$0"))/..)

if [[ "$TEST_WEBDRIVER_HUB" == "" ]]; then
    TEST_WEBDRIVER_HUB="192.168.0.6"
fi # [[ "$TEST_WEBDRIVER_HOST" == "" ]]

if [[ "$TEST_HOST_IP" == "" ]]; then
    TEST_HOST_IP="192.168.0.23"
fi # [[ "$TEST_HOST_IP" == "" ]]

if [[ "$1" == "firefox" ]]; then
    export TEST_BROWSER="firefox:http://$TEST_WEBDRIVER_HUB:4444/wd/hub"
    export TEST_HOST="$TEST_HOST_IP:8000"
    mvn test -Dcucumber.options="-t ~@nofirefox"
elif [[ "$1" == "chrome" ]]; then
    export TEST_BROWSER="chrome:http://$TEST_WEBDRIVER_HUB:4444/wd/hub"
    export TEST_HOST="$TEST_HOST_IP:8001"
    mvn test -Dcucumber.options="-t ~@nochrome"
elif [[ "$1" == "ie" ]]; then
    export TEST_BROWSER="ie:http://$TEST_WEBDRIVER_HUB:4444/wd/hub"
    export TEST_HOST="$TEST_HOST_IP:8002"
    mvn test -Dcucumber.options="-t ~@noie"
elif [[ "$1" == "ie8" ]]; then
    export TEST_BROWSER="ie:http://192.168.56.101:5555/"
    export TEST_HOST="192.168.56.1:8004"
    mvn test -Dcucumber.options="-t ~@noie"
elif [[ "$1" == "edge" ]]; then
    export TEST_BROWSER="edge:http://$TEST_WEBDRIVER_HUB:4444/wd/hub"
    export TEST_HOST="$TEST_HOST_IP:8003"
    mvn test -Dcucumber.options="-t ~@noedge"
else
    cat << EOF
    Environment Defaults:

    TEST_WEBDRIVER_HUB="192.168.0.6"
    TEST_HOST_IP="192.168.0.23"  # docker: 172.17.0.1

    You need to pass the browser: firefox, chrome, ie or edge.
EOF
fi

