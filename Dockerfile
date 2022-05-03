FROM openjdk:11
COPY . /tmp
WORKDIR /tmp
ENTRYPOINT ["java","src/main/java/main.java"]