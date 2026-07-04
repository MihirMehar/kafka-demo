pipeline {
    agent any
    stages {
        stage('Checkout Code') { ... }
        stage('Build') { ... }
        stage('Run Tests') { ... }
        stage('Build Docker Image') { ... }
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
        stage('Done') { ... }
    }
    post { ... }
}