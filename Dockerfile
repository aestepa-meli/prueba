FROM adoptopenjdk/openjdk8
ARG JAR_FILE=$JAR_FILE
COPY target/${JAR_FILE} /${JAR_FILE}
ENTRYPOINT exec java $JAVA_OPTS -jar ${JAR_FILE}