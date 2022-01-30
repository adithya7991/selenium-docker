pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                bat "docker build -t=1151997/automation-code ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
                    // pushing 2 times, 1 with latest tag and another with tag=jenkins build number
			        bat "docker login --username=${user} --password=${pass}"                    
			        bat "docker push 1151997/automation-code:latest"
			    }                           
            }
        }
    }
}