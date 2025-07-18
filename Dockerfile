# Используем официальный образ JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Устанавливаем рабочую директорию
WORKDIR /app

# 1. Копируем только необходимые для сборки файлы
# Сначала копируем файлы конфигурации Gradle
COPY gradlew .
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/
COPY build.gradle .
COPY settings.gradle .

# Затем копируем исходный код
COPY src src

# 2. Даем права на выполнение gradlew
RUN chmod +x gradlew

# 3. Устанавливаем переменные окружения для Gradle
ENV GRADLE_USER_HOME=/tmp/.gradle
RUN mkdir -p $GRADLE_USER_HOME && \
    chmod -R 777 $GRADLE_USER_HOME

# 4. Собираем приложение с очисткой кэша
RUN ./gradlew installDist --no-daemon --stacktrace && \
    rm -rf $GRADLE_USER_HOME/caches

# 5. Запускаем приложение
CMD ["./build/install/app/bin/app"]