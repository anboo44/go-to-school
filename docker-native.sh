#! /bin/sh

./gradlew :discovery-service:bootBuildImage
./gradlew :gateway-service:bootBuildImage
./gradlew :auth-service:bootBuildImage
./gradlew :core-service:bootBuildImage
./gradlew :report-service:bootBuildImage
./gradlew :logger-service:bootBuildImage

cd docker
docker-compose up -d
