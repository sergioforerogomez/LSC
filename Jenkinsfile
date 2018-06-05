pipeline {
    agent any
    stages {
        stage('Prepare'){
            steps{
				sh 'rm -rf LSC Jenkinsfile Dockerfile ; pwd ; ls ; git clone https://github.com/sergioforerogomez/LSC.git'
            }
        }

        stage('Build') {
            steps {
				sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean install -Dmaven.test.skip -f '{}' \\;  "
            }
        }
		
        /*stage('Test'){
            steps {
				sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean verify -f '{}' \\; "
                publishHTML (target: [
					allowMissing: false,
					alwaysLinkToLastBuild: true,
					keepAll: true,
					reportDir: 'LSC/LSC/serenity/target/site/serenity',
					reportFiles: 'index.html',
					reportName: "Serenity"
                ])
            }
        }*/
		
        stage('Deploy') {
            steps {
                script {
					try {
						sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal killall java'
      		        }
                    catch(error) {
						echo "No java processes found in 172-31-93-85"
					}
					try {
      		            sh 'ssh ubuntu@172.31.95.218 killall java'
      		        }
                    catch(error) {
						echo "No java processes found in 172.31.95.218"
					}
					try {
						sh 'ssh ubuntu@172.31.95.218 killall mongod'
                    }
                    catch(error) {
						echo "No mongo processes in 172.31.95.218 in 172.31.95.218"
      	            }
                }
				
				// Gateway
				sh 'scp -r LSC/LSC/gateway ubuntu@ip-172-31-93-85.ec2.internal:/home/ubuntu/LSC/LSC/'
				sh 'scp -r LSC/gatewaystart.sh ubuntu@ip-172-31-93-85.ec2.internal:/home/ubuntu/'
				sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal sh gatewaystart.sh'

				// LSC server
				sh 'scp -r LSC/LSC/users LSC/LSC/common LSC/LSC/dictionary ubuntu@172.31.95.218:/home/ubuntu/LSC/LSC/'
				sh 'scp -r LSC/javastart.sh ubuntu@172.31.95.218:/home/ubuntu/'
				sh 'ssh ubuntu@172.31.95.218 sh javastart.sh'
			}
        }
    }
}
