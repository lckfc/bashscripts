pipeline{
    agent any
    environment {
        HOST1 = 'centostest1'
        HOST2 = 'centostest2'
         }
        stages {
            stage('Checking backup directories.') {
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
            stage('Pulling backup .tar from GIT.') {
                steps {
                    sshagent(['centostest1-root']) {
                    echo "-------------------------------------------------------------------------------------------------------"
                    echo "                                        Retrieving Backup TAR"
                    echo "-------------------------------------------------------------------------------------------------------"
                    sh '''
                    ssh root@$HOST1 'curl -L -O https://github.com/lewis-git/jenkins/blob/master/backup001.tar > /opt/wildflybackup/backup001.tar'      
                       '''
                    }
                }
            }
        }
    }
