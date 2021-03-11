# Build native Docker image and run it
./mvnw package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t quarkus-soap-cxf-example:latest .
docker run -i --rm -p 7000:7000 quarkus-soap-cxf-example:latest