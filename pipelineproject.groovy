pipeline{
    agent none
    environment {
        HOST1 = 'centostest1'
        HOST2 = 'centostest2'
         }
        stages{
            stage('Stage 1' ){
                steps{
                    node('master')
                    sshagent(['centostest1-root'])
                    {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Running stage 1."    
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'ls -al /tmp'
                    #ssh root@$HOST1 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; fi'
                    #ssh root@$HOST2 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; fi'
			        '''
                    }
                    }
                }
            stage('STAGE2'){
                steps{
                    node('master'){
                        echo "-------------------------------------------------------------------------------------------------------"
                        echo "                                        Running stage 2."
                        echo "-------------------------------------------------------------------------------------------------------"
                        sh '''
                        ssh $HOST1 ls -al
			            '''
                    }
                }
            }
		}
            post {
                failure {
                node('master') {
                    wrap([$class: 'BuildUser']) {
                    emailext body: "", subject: "ERROR - Build: [${JOB_NAME}] User: [${env.BUILD_USER}]", to: 'lkemball98@gmail.com'
                        }
                    }
                }
                success {
                node('master') {
                    wrap([$class: 'BuildUser']) {
                    emailext body: "", subject: "SUCCESS - Build: [${JOB_NAME}] User: [${env.BUILD_USER}]", to: 'lkemball98@gmail.com'
                }
            }
        }
    }
}

