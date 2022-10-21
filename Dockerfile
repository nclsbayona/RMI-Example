FROM docker.io/openjdk:20-slim-buster AS build
ADD ./Tienda /home
WORKDIR /home
RUN apt-get update && apt-get install -y iputils-ping net-tools
ENTRYPOINT ["bash"]
CMD ["sleep", "infinity"]