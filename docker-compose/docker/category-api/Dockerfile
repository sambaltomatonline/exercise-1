FROM openjdk:8u102

LABEL maintainer="sambaltomat.online@gmail.com"

EXPOSE 13000

RUN mkdir /opt/app

ADD category-api.jar /opt/app/category-api.jar

WORKDIR /opt/app

ENTRYPOINT ["java","-jar","category-api.jar"]