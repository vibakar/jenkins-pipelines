pipelineJob("python-git") {
    displayName("Python Git")

    parameters {
        nonStoredPasswordParam('GIT_TOKEN', '')
    }

    definition {
        cpsscm {
            scm {
                git {
                    remote {
                        url("https://github.com/vibakar/python-pipeline.git")
                    }
                    branch("master")
                }
            }
            scriptPath("jenkinsfiles/pythonGit.groovy")
        }
    }
}