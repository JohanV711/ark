# Ark
Práctica de Computación Móvil Ubicua y en la Nube.


Para montar el entorno de docker, desde el directorio app/contenedores:

1. Ejecutar el script start.sh para instalar node_exporter en el servidor:

```bash
./start.sh
```
2. Dar permisos al usuario 472:
```bash
sudo chown -R 472:472 grafana 
```

3. Lanzar docker en segundo plano:

```bash
sudo docker-compose up -d
```

