pipeline {
    agent any
    stages {
        stage('Prepare'){
            steps{
      				// slackSend(color: '#FFFF00', message: "STARTED: Job '${env.pipeline} [${env.}]' (${env})")
      				sh 'rm -rf LSC Jenkinsfile Dockerfile ; pwd ; ls ; git clone https://github.com/sergioforerogomez/LSC.git'
            }
        }
        stage('Build') {
            steps {
				sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean package -f '{}' \\;  "
            }
        }
        /*stage('Test'){
            steps {
				        // sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean verify -f '{}' \\; "
                // sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn test -f '{}' \\;"
                publishHTML (target: [
        					allowMissing: false,
        					alwaysLinkToLastBuild: false,
        					keepAll: true,
        					reportDir: 'LSC/LSC/serenity/target/site/serenity',
        					reportFiles: 'index.html',
        					reportName: "Report"
                ])
            }
        }*/
        stage('Deploy') {
            steps {
            // input 'do you approve deploy?'

            script {
				        try {
					           sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal killall java'
		                 sh 'ssh ubuntu@172.31.81.225 killall java'
		                 }
                catch(error) {
					           echo "No java process"
				        }
		            try {
	                   sh 'ssh ubuntu@172.31.81.225 killall mongod'
                     }
               catch(error) {
					          echo "No mongo process"
	             }
            }
            // Gateway
            sh 'scp -r LSC/ ubuntu@ip-172-31-93-85.ec2.internal:/home/ubuntu/'
            sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal sh ./LSC/gatewaystart.sh'
            sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal ps'

            // LSC server

      			sh 'scp -r LSC/ ubuntu@172.31.81.225:/home/ubuntu/'
            sh 'ssh ubuntu@172.31.81.225 ls'
            sh 'ssh ubuntu@172.31.81.225 ls LSC/'
      			sh 'ssh ubuntu@172.31.81.225 rm -rf ./LSC/LSC/gateway/'
            sh 'ssh ubuntu@172.31.81.225 ls ./LSC/LSC/'
            sh 'ssh ubuntu@172.31.81.225 find ./LSC/LSC -name *SNAPSHOT.jar'
            sh 'ssh ubuntu@172.31.81.225 sh ./LSC/javastart.sh'
            sh 'ssh ubuntu@172.31.81.225 ps'
			}
        }
    }
}
