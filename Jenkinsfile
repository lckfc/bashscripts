pipeline{
    agent none
    environment {
        // version: x.x.x DD/MM/CCYY
        EMAIL = 'lkemball98@gmail.com'
        HOST1 = 'centostest1'
        HOST2 = 'centostest2'
         }
        stages{
            stage('STAGE1' ){
                steps{
                    node('master'){
                        echo "-------------------------------------------------------------------------------------------------------"
                        echo "Running stage 1"
                        echo "-------------------------------------------------------------------------------------------------------"
                        sh '''
                        ssh root@$HOST1 hostnamectl set-hostname $HOST1
                        '''
                        }
                    }
                }
            stage('STAGE2'){
                steps{
                    node('master'){
                        echo "-------------------------------------------------------------------------------------------------------"
                        echo "Running stage 2"
                        echo "-------------------------------------------------------------------------------------------------------"
                        sh '''
                        ssh root@$HOST2 hostnamectl set-hostname $HOST2
                        '''
                    }
                }
            }
		}
            post {
                failure {
                node('master') {
                    wrap([$class: 'BuildUser']) {
                    emailext body: "", subject: "ERROR - Build: [${JOB_NAME}] User: [${env.BUILD_USER}]", to: '$EMAIL'
                        }
                    }
                }
                success {
                node('master') {
                    wrap([$class: 'BuildUser']) {
                    emailext body: "", subject: "SUCCESS - Build: [${JOB_NAME}] User: [${env.BUILD_USER}]", to: '$EMAIL'
                }
            }
        }
    }
}
