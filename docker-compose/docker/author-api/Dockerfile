FROM openjdk:8u102

LABEL maintainer="sambaltomat.online@gmail.com"

EXPOSE 13000

RUN mkdir /opt/app

ADD author-api.jar /opt/app/author-api.jar

WORKDIR /opt/app

ENTRYPOINT ["java","-jar","author-api.jar"]