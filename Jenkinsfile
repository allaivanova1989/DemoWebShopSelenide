pipeline {
    agent any
   parameters {
        choice(name: "testClass", choices: ["NegativeTest", "PositiveTest"], description: "Run test class")
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/allaivanova1989/DemoWebShopSelenide.git'



                // To run Maven on a Windows agent, use
                bat "mvn clean test -Dtest=$params.testClass"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'

                }
            }
        }
        stage('report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]])
                }
            }
        }
    }
}