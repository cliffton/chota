FROM openjdk:8
ARG profile=dev
ARG db_url=jdbc:h2:mem:bootexample;MODE=MySQL;DB_CLOSE_ON_EXIT=FALSE
ARG db_username=8080
ARG db_password=chota
ENV spring_profiles_active=${profile}
ENV spring_datasource_url=${db_url}
ENV spring_datasource_username=${db_username}
ENV spring_datasource_password=${db_password}
RUN mkdir chota
COPY target/chota-0.0.1-SNAPSHOT.jar chota/chota.jar
EXPOSE 8080
WORKDIR chota
ENTRYPOINT ["java","-jar","chota.jar"]
