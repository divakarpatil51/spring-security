//Declarative pipeline
pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven3'
    }
    stages {
        stage('Build') { 
            steps {
            	echo 'Building'
                sh 'mvn -B -DskipTests clean package'          
            }
        }
        stage('Test') { 
            steps {
            	echo 'Testing'
                sh 'mvn test' 
            }
            post {
            	always {
            		junit 'target/surefire-reports/*.xml'
            	}
            }
        }
    }
}
