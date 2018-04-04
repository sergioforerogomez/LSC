  pipeline {
    agent any
    stages {
        stage('Prepare'){
            steps{
            //slackSend(color: '#FFFF00', message: "STARTED: Job '${env.pipeline} [${env.}]' (${env})")
            sh 'rm -rf LSC Jenkinsfile Dockerfile ; pwd ; ls ; git clone https://github.com/sergioforerogomez/LSC.git'
            }
        }
        stage('Build') {
            steps {
              sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean install -f '{}' \\; "
            }
        }
        stage('Test'){
            steps {
					       echo ' Testing'
                 sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn clean verify -f '{}' \\; "
                 sh " find ./LSC/LSC/ -name 'pom.xml' -exec mvn test -f '{}' \\;"
            }
        }
        stage('Deploy') {
            steps {
						echo 'Deploying'
            //input 'do you approve deploy?'
            sh 'scp -r LSC/ ubuntu@ip-172-31-93-85.ec2.internal:/home/ubuntu/'
            script {
              try {
                sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal killall java'
              }
              catch(error) {
                echo "no java process"
              }
              try {
                sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal killall mongod'
              }
              catch(error) {
                echo "no mongo process"
              }
            }
            sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal sh ./LSC/javastart.sh'
            sh 'ssh ubuntu@ip-172-31-93-85.ec2.internal ps'
            }
        }
    }
}
