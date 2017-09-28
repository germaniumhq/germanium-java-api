properties([
    parameters([
        string(name: 'LOCAL_PROXY', defaultValue: '172.17.0.1:3128',
                description: 'Squid proxy to use for fetching resources'),
        string(name: 'SOURCES_URL', defaultValue: 'http://192.168.0.2:10080/germanium/germanium-java.git',
                description: 'Squid proxy to use for fetching resources'),
        string(name: 'DOCKER_HOST_IP', defaultValue: '192.168.0.2',
                description: 'The host where the browsers will try to connect. The port will be randomly allocated.'),
        booleanParam(name: 'RUN_CHROME_TESTS', defaultValue: true,
                description: 'Should the chrome tests run.')
    ])
])

RUN_CHROME_TESTS = Boolean.valueOf(RUN_CHROME_TESTS)

stage('Build Docker Container') {
    node {
        withCredentials([file(credentialsId: 'NEXUS_SETTINGS_XML', variable: 'NEXUS_SETTINGS_XML')]) {
            deleteDir()
            checkout scm

            sh """
                cp ${env.NEXUS_SETTINGS_XML} ./jenkins/scripts/settings.xml
                chmod 666 ./jenkins/scripts/settings.xml
            """

            dockerBuild file: './jenkins/Dockerfile.java8.build',
                build_args: [
                    "http_proxy=http://${LOCAL_PROXY}",
                    "https_proxy=http://${LOCAL_PROXY}",
                    "ftp_proxy=http://${LOCAL_PROXY}"
                ],
                tags: ['germanium_java8']
        }
    }
}

def name = 'ge-java-' + getGuid()

stage('Compile Germanium') {
    node {
        dockerRun image: 'germanium_java8',
            name: name,
            env: [
                "SOURCES_URL=${SOURCES_URL}"
            ],
            links: [
                'nexus:nexus'
            ],
            command: 'bash -l -c /scripts/compile-germanium.sh'
    }
}

stage('Commit Docker Image') {
    node {
        sh """
            docker commit ${name} ${name}
        """
    }
}

stage('Run Tests') {
    def germaniumParallelTests = [:]

    if (RUN_CHROME_TESTS) {
        germaniumParallelTests."Chrome (Local)" = {
            node {
                def port = getRandomPort()
                dockerRun image: name,
                    privileged: true,
                    remove: true,
                    env: [
                        'TEST_BROWSER=chrome',
                        "TEST_HOST=${DOCKER_HOST_IP}:${port}",
                        'DISPLAY=vnc:0'
                    ],
                    links: [
                        'vnc:nvc'
                    ],
                    ports: [
                        "${port}:${port}"
                    ],
                    volumes: [
                        '/opt/host:/opt/container:rw'
                    ],
                    command: 'bash -l -c /scripts/test-drivers.sh'
            }
        }
    }

    parallel(germaniumParallelTests)
}

