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

       stage('Deploy') {   // <-- yeh naya add kiya
            steps {
                echo 'Deploying new container...'
                sh 'docker stop kafka-demo-app-container || true'
                sh 'docker rm kafka-demo-app-container || true'
                sh '''
                    docker run -d \
                    --name kafka-demo-app-container \
                    --network kafka-demo_default \
                    -p 8080:8080 \
                    -e SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:29092 \
                    kafka-demo-app:latest
                '''
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