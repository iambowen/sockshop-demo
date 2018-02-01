pipeline {
    agent any

    stages {
        stage('Build Mesher-Microservice Image') {
            steps {
                echo 'Building Mesher-Microservices Images'
                sh 'docker login -u cn-north-1@UINZOXGKR7GQXBUVZGN0 -p 1ebee63f40f55d8b8a4346a486838161703dfd95532f44cf39cfd63ca3d4d472 registry.cn-north-1.huaweicloud.com'
                sh 'bash -x scripts/pipeline/build_go_chassis_images.sh'

            }
        }
    }
}
