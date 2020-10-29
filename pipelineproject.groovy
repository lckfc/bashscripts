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
                    echo "                                        Sorting Directories"    
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; echo "Directory now created."; fi'
                    ssh root@$HOST2 'if [[ -d /opt/wildflybackup ]]; then echo "Directory already exists, moving on..."; else mkdir -v /opt/wildflybackup; echo "Directory now created."; fi'
                        '''
                    }
                }
            }
            stage('STAGE2') {
                steps {
                    sshagent(['centostest1-root']) {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Retrieving Backup TAR"
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'cd /opt/wildflybackup'; 'curl --lewis-git "caspyin:SuperBambiLC2k16" https://github.com/lewis-git/jenkins/blob/master/backup001.tar
			        '''
                    }
                }
            }
        }
    }