FROM openjdk:8u102

LABEL maintainer="sambaltomat.online@gmail.com"

EXPOSE 13000

RUN mkdir /opt/app

ADD order-api.jar /opt/app/order-api.jar

WORKDIR /opt/app

ENTRYPOINT ["java","-jar","order-api.jar"]