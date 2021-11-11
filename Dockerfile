FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/market-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#docker build -t docker-market .
#docker ps
#docker image ls
#docker run -d -p 5000:5000 -t spring-market
#docker stop 1f62401019a5


#docker tag spring-market repo/spring-market
#docker push repo/spring-market

#docker login --username=roman2301
#Limit2301

#docker login --username=docker.io

#docker search centos



#docker tag spring-market roman2301/spring-market:spring-market
#docker push roman2301/spring-market:spring-market


