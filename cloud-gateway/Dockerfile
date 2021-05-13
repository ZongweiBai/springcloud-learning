FROM openjdk:16-jdk-alpine3.13

ENV WORKDIR="/usr/local/modules/cloud-gateway"
ENV JAVA_OPTS="-XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms2048m -Xmx2048m -Dspring.profiles.active=prod"
ENV JAVA_AGENT="-javaagent:$WORKDIR/agent/skywalking-agent.jar -Dskywalking.agent.service_name=cloud-gateway -Dskywalking.agent.keep_tracing=true"
ENV JVM_OPTS="-Djava.security.egd=file:/dev/./urandom -DAPP_CONFIG=$WORKDIR/config -DLOG_PATH=$WORKDIR/log -Dspring.config.additional-location=$WORKDIR/config/"

VOLUME ["$WORKDIR/config", "$WORKDIR/log", "$WORKDIR/agent", "$WORKDIR/static"]

RUN mkdir -p $WORKDIR/config \
     && mkdir -p $WORKDIR/log

WORKDIR $WORKDIR

ARG JAR_FILE
ADD $JAR_FILE cloud-gateway.jar

EXPOSE 15007

ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS $JAVA_AGENT -jar $JVM_OPTS cloud-gateway.jar"]