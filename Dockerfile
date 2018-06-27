FROM java:8
ADD target/zcmzjp.jar zcmzjp.jar
EXPOSE 8088
LABEL name=zcmzjp
ENTRYPOINT ["java", "-jar", "zcmzjp.jar"]