pipeline{
    agent any
    environment {
        HOST1 = 'centostest1'
        HOST2 = 'centostest2'
         }
        stages {
            stage('Stage 1') {
                steps {
                    sshagent(['centostest1-root']) {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Running stage 1."    
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; echo "Directory now created."; fi'
                    ssh root@$HOST2 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; echo "Directory now created."; fi'
                    ssh root@$HOST1 'cd /opt/wildflybackup' && 'curl --lewis-git "caspyin:SuperBambiLC2k16" https://github.com/lewis-git/jenkins/blob/master/backup001.tar
                    '''
                    }
                }
            }
            stage('STAGE2') {
                steps {
                    sshagent(['centostest1-root']) {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Running stage 2, ignore..."
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    #ssh root$HOST1 ls -al
			        '''
                    }
                }
            }
        }
    }