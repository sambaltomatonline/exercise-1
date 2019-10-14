FROM openjdk:8u102

LABEL maintainer="sambaltomat.online@gmail.com"

EXPOSE 13000

RUN mkdir /opt/app

ADD book-api.jar /opt/app/book-api.jar

WORKDIR /opt/app

ENTRYPOINT ["java","-jar","book-api.jar"]