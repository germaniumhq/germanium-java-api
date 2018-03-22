stage('Build Germanium') {
    node {
        deleteDir()

        checkout scm

        dockerRm containers: [
            'germanium_java_ok'
        ]

        dockerBuild file: './Dockerfile',
            tags: ['germanium_test:java']

        dockerRun image: 'germanium_test:java',
            name: 'germanium_java_ok',
            env: [
                'DISPLAY=vnc-server:0',
                'TEST_REUSE_BROWSER=1',
                'TEST_BROWSER=chrome',
                "MAVEN_EXTRA_PARAMETERS=-Dcucumber.options='-t ~@nochrome'"
            ],
            networks: ['vnc'],
            volumes: [
                '/dev/shm:/dev/shm:rw'
            ]

        dockerCommit name: 'germanium_java_ok',
            image: 'germanium_java'
    }
}
