#!/usr/bin/env bash

set -e

cmd=$(basename $0)

sub_help(){
    echo "Usage: $cmd <sub-command> [options]"
    echo "Sub-commands:"
    echo "    help              Show help"-efk
    echo "    checkout-code     Checkout code from git"
    echo "    compile           Compile java code"
    echo "    sonar-scan        Run sonar scanner"
    echo "    quality-gate      Check quality gate"
    echo "    docker-build      Build project with docker"
    echo "    docker-up         Start services via docker"
    echo "    docker-push       Push image to Nexus repository via docker"
    echo ""
    echo "For help with each sub-command run:"
    echo "$cmd <sub-command> -h|--help"
    echo ""
}

sub_checkout-code(){
    # -------------CLONE-----------------
    # cd /home/gitlab-runner/repos/
    # rm -rf ./spring-ci-efk
    # git config --global user.name 'truongbb96'
    # git config --global user.email 'truongbb@itsol.vn'
    # git clone https://truongbb96:<password>@git.myitsol.com/truongbb96/spring-ci-efk.git
    # cd ./spring-ci-efk
    # ------------PULL----------------
    cd /home/gitlab-runner/repos/spring-ci-efk
    git checkout
    git pull
}

sub_compile(){
    _docker_mvn compile -DskipTests
}

sub_sonar-scan(){
#    chmod -R 777 ./target/ #add new
#    rm -rf ./target/*
    mvn clean --settings settings.xml package sonar:sonar \
        -Dsonar.projectName='SpringCiEfk' \
        -Dsonar.projectKey='spring-ci-efk'
}

sub_quality-gate(){
    qualityGateStatus=$(curl --request POST \
        --url http://192.168.3.60:9000/api/qualitygates/project_status?projectKey=spring-ci-efk \
        --header 'cache-control: no-cache' --header 'content-type: application/json' |  jq -r  '.projectStatus.status')
    # echo "Quality gate status: " ${qualityGateStatus}
    if [[ ${qualityGateStatus} != "OK" ]]
    then
    # echo "*******QUALITY GATE FAILED!**********"
        echo "NOK"
    else
    # echo "*******QUALITY GATE PASSED!**********"
        echo "OK"
    fi
}

sub_docker-build(){
    if [[ -d 'target' ]]
    then
        echo "Already packaged!"
    else
        echo "Need to package before building with docker-compose.."
        sub_package
    fi
    docker-compose -f deploy/docker-compose.yml build
}

sub_docker-up(){
    if [[ -d 'target' ]]
    then
        echo "Already packaged!"
    else
        echo "Need to package before building with docker-compose.."
        sub_package
    fi

    docker-compose -f deploy/docker-compose.yml up -d
}

sub_docker-push(){
    if [[ -d 'target' ]]
    then
        echo "Already packaged!"
    else
        echo "Need to package before building with docker-compose.."
        sub_package
    fi

    docker-compose -f deploy/docker-compose.yml push
}

_docker_mvn() {
    docker run -i --rm \
        -v "$PWD:/usr/src/" \
        -v "$HOME/.m2:/root/.m2" \
        -w /usr/src/ \
		maven:3.6.1-jdk-11 \
        mvn --settings settings.xml -U clean $@
}

sub_package() {
    _docker_mvn package -DskipTests
}

sub_setup() {
    echo "Setup host for deploying app, require sudo privileges"

    #echo "Configuring repo..."
    #sudo rm -f /etc/yums.repo.d/*.repo
    #sudo cp deploy/docker/local.repo /etc/yum.repos.d/
    #sudo yum update -y

    #echo "Install packages..."
    sudo yum install -y docker-ce fish vim git telnet python-pip

    echo "Configuring docker..."
    sudo mkdir -p /etc/docker
    sudo cp deploy/docker/daemon.json /etc/docker/
    sudo systemctl daemon-reload
    sudo systemctl enable docker
    sudo systemctl restart docker

    #echo "Configuring pip..."
    #sudo mkdir -p /root/.config/pip/
    #sudo cp deploy/docker/pip.conf /root/.config/pip/

    #echo "Install Docker-compose via pip.."
    #sudo pip install docker-compose

    echo "Configuring sysctl..."
    sudo cp deploy/docker/sysctl.conf /etc/
    sudo sysctl --load=/etc/sysctl.conf

    #echo "Configuring system limits..."
   # sudo cp deploy/docker/limits.conf /etc/security/
}

sub_command=$1

case sub_command in
    "" | "-h" | "--help")
        sub_help
        ;;
    *)
        shift
        sub_${sub_command} $@
        if [[ $? = 127 ]]; then
            echo "Error: '$sub_command' is not a known sub-command." >&2
            echo "Run '$cmd --help' for a list of known sub-commands." >&2
            exit 1
        fi
        ;;
esac