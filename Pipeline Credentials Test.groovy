pipeline {
    agent none 
           stages {
                stage ('Reference Credentials') {
                    steps {
                    node('jenkins-slave'){
                    withCredentials([usernamePassword(credentialsId: '72543c5c-f968-4ad7-a1bf-1f86315952ae', usernameVariable: 'SOLVE3ENT_ID', passwordVariable: 'SOLVE3ENT_PW')]) {
                    echo SOLVE3ENT_ID
                    echo SOLVE3ENT_PW
                    sh 'echo ${SOLVE3ENT_ID} > /tmp/id.txt'
                    sh 'echo ${SOLVE3ENT_PW} > /tmp/pw.txt'
                    sh 'cat /tmp/pw.txt'
                    }
                }
            }
        }
                stage ('Reference SSH Keys') {
                    steps {
                    node('jenkins-slave'){
                    sshagent(['7c7f21b9-5b85-4c69-adbc-5f73ae0d4c32']) {
                    sh '''
                    ssh root@centos7vm2 'ls -al /tmp'
                    '''
                    }
                }
            }
        }
    }
}