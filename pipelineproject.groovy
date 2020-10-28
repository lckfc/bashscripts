pipeline{
    agent any
    environment {
        HOST1 = 'centostest1'
        HOST2 = 'centostest2'
         }
        stages {
            stage('Stage 1') {
                steps {
<<<<<<< HEAD
=======
                    node('master') {
>>>>>>> 55b6336fcbf678a3bfb37c4a85d18538b727a9f4
                    sshagent(['centostest1-root']) {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Running stage 1."    
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'ls -al /tmp'
                    #ssh root@$HOST1 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; fi'
                    #ssh root@$HOST2 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; fi'
			        '''
<<<<<<< HEAD
                    }
                }
            }
            stage('STAGE2') {
                steps {
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
=======
                        }
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
}
>>>>>>> 55b6336fcbf678a3bfb37c4a85d18538b727a9f4
