FROM java:jre8
WORKDIR /app
COPY helloworld.jar .
ENV JAVA_OPTS="\
-Dcom.sun.management.jmxremote.rmi.port=19728 \
-Dcom.sun.management.jmxremote.port=19728 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Djava.rmi.server.hostname=MyIp"
ENTRYPOINT java ${JAVA_OPTS} -jar /app/helloworld.jar

# CMD ["java","-Dcom.sun.management.jmxremote.rmi.port=19728","-Dcom.sun.management.jmxremote.port=19728","-Dcom.sun.management.jmxremote.ssl=false","-Dcom.sun.management.jmxremote.authenticate=false","-Djava.rmi.server.hostname=myIp","-jar","/app/helloworld.jar"]