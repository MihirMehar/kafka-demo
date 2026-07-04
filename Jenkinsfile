pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t kafka-demo-app:latest .'
            }
        }

        stage('Done') {
            steps {
                echo '✅ Pipeline completed successfully! Build + Test + Docker Image — sab automated ho gaya.'
            }
        }
    }

    post {
        success {
            echo '🎉 Build SUCCESS'
        }
        failure {
            echo '❌ Build FAILED — check logs above'
        }
    }
}