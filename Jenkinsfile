properties([
    safeParameters(this, [
        booleanParam(name: 'RUN_CHROME_TESTS', defaultValue: true,
                description: 'Should the chrome tests run'),
        string(name: 'DOCKER_HOST_IP', defaultValue: '192.168.0.51',
                description: 'Squid proxy to use for fetching resources')
    ])
])

safeParametersCheck(this)

stage('Build Germanium') {
    node {
        deleteDir()
        checkout scm

        docker.build('germanium_java8')
    }
}

stage('Run Tests') {
    def germaniumParallelTests = [:]

    if (RUN_CHROME_TESTS) {
        germaniumParallelTests."Chrome (Local)" = {
            node {
                def port = getRandomPort()

                dockerInside image: 'germanium_java8',
                    privileged: true,
                    env: [
                        'TEST_BROWSER=chrome',
                        "TEST_HOST=${DOCKER_HOST_IP}:${port}",
                        'DISPLAY=vnc:0'
                    ],
                    links: [
                        'vnc-server:vnc'
                    ],
                    ports: [
                        "${port}:${port}"
                    ],
                    code: {
                        sh """
                            cd /src
                            mvn test -Dcucumber.options="-t ~@nochrome"
                        """
                    }
            }
        }
    }

    parallel(germaniumParallelTests)
}

