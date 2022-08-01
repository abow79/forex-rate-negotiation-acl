pipeline {

    agent {
        label "podman-agent"
    }



    environment {
        REGISTRY = "harbor.fstop.com.tw"
        NAMESPACE = "xp"
        IMAGE_NAME= "forex-rate-negotiation-acl"
        CREDENTIALSID = ""
    	VERSION = VersionNumber([
        versionNumberString : '${BUILD_YEAR}.${BUILD_MONTH}.${BUILD_DAY}.${BUILDS_THIS_MONTH}',
        projectStartDate : '2022-07-11'
        ]);
    }

    stages {


        stage("Maven Build") {
            steps {
                sh 'mvn clean package'
          }
        }
        stage("Podman Build") {
            steps {
                sh 'podman build -t ${IMAGE_NAME}:${VERSION} .'
            }
        }
        stage("Publish image to Harbor") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'harbor-credentials',
                                                   usernameVariable: 'USERNAME',
                                                   passwordVariable: 'PASSWORD')]) {
                sh """
                    #!/bin/bash
                    podman login -u ${USERNAME} -p ${PASSWORD} ${REGISTRY}
                    podman tag localhost/${IMAGE_NAME}:${VERSION} ${REGISTRY}/${NAMESPACE}/${IMAGE_NAME}:${VERSION}
                    podman push ${REGISTRY}/${NAMESPACE}/${IMAGE_NAME}:${VERSION}
                    podman logout ${REGISTRY}
                """
                }
            }
        }

         stage("Deployment tiger-workload Cluster") {
            steps {
		sh 'echo "${VERSION}"'
                sh 'kubectl config use-context workload-1-admin@workload-1'
		sh 'envsubst < deploy/deployment.yaml | kubectl apply -f -'
            }
        }


    }
}