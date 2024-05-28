### Setup MacOs

* Install podman to have docker
* Set env vars:
```shell
export DOCKER_HOST=unix://$(podman machine inspect --format '{{.ConnectionInfo.PodmanSocket.Path}}')
DOCKER_CERT_PATH=$path_to_cert
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
```