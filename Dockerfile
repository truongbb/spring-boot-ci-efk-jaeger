ARG JDK_BASE_IMAGE
FROM ${JDK_BASE_IMAGE}

ARG PROJECT_VERSION
ENV PROJECT_VERSION ${PROJECT_VERSION}

WORKDIR /app
COPY target/spring-ci-efk-$PROJECT_VERSION.jar spring-ci-efk.jar

ENTRYPOINT ["java","-Djava.security.egd=file:///dev/urandom","-jar", "/app/spring-ci-efk.jar"]
CMD ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9013","--spring.profiles.active=native,dev"]
