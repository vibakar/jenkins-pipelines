pipeline {
    agent {
        label "python" //docker agent should have label python in jenkins with a docker image containing java and python3
    }
    stages{
        stage("Stage 1") {
            steps {
                sh "python3 --version"
            }
        }
    }
}