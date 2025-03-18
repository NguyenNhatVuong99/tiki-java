FROM eclipse-temurin:21-jdk-alpine
LABEL author="Vuong Nhat Nguyen"
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY src ./src

CMD ["./mvnw", "spring-boot:run"]