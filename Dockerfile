FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

#WORKDIR /opt/starter
WORKDIR /opt/springboot
COPY /target/api-rest*.jar api_rest_mysql.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080
EXPOSE 5005

CMD java ${ADDITIONAL_OPTS} -jar api_rest_mysql.jar --spring.profiles.active=${PROFILE}