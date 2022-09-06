library identifier: 'jenkins-libraries@master', retriever: modernSCM(
[$class: 'GitSCMSource',
remote: 'https://github.com/vibakar/jenkins-libraries.git'])

pipeline {
    agent {
        label "python" //docker agent should have label python in jenkins with a docker image containing java and python3
    }
    environment {
        GIT_ORG = "vibakarorg"
        GIT_REPO = "python-repo"
        GIT_REPO_PRIVACY_SETTING = "Y"
    }
    stages{
        stage("Init") {
            steps {
                script {
                    env.GIT_TOKEN = "${params.GIT_TOKEN}"
                    env.GIT_ORGANIZATION = "${params.GIT_ORGANIZATION}"
                    env.GIT_REPO_NAME = "${params.GIT_REPO_NAME}"
                    if(params.GIT_REPO_PRIVACY_SETTING) {
                        env.GIT_REPO_PRIVACY_SETTING = "Y"
                    } else {
                        env.GIT_REPO_PRIVACY_SETTING = "N"
                    }
                    sh "python3 --version"
                }
            }
        }

        stage("Run Python Script") {
            steps {
                writeFile file:'git.py', text:libraryResource("git.py")
                sh "python3 -m pip install pygithub"
                sh "python3 git.py -a createRepo"
            }
        }
    }
}