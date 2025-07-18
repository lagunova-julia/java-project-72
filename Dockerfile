# Используем более легкий образ с JDK 21 (LTS)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Копируем только необходимые для сборки файлы
COPY gradle ./gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Устанавливаем Gradle Wrapper (лучше использовать его, чем глобальную установку Gradle)
RUN chmod +x gradlew && \
    ./gradlew --version && \
    # Чистим кэш apt, чтобы уменьшить размер образа
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Собираем приложение с очисткой кэша
RUN ./gradlew installDist --no-daemon --stacktrace \
    # Очищаем кэш Gradle после сборки
    && rm -rf /root/.gradle/caches

# Запускаем приложение
CMD ["./build/install/app/bin/app"]