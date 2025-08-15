# Guía de Despliegue Completo

Esta guía te explica paso a paso cómo desplegar el sistema de gestión de productos y categorías usando Docker Compose.

---

## 1. Prerrequisitos

- Tener instalado Docker y Docker Compose en el servidor Linux o máquina local.
- Tener el proyecto completo con la siguiente estructura:
  - Microservicio de categorías (`categorias`)
  - Microservicio de productos (`products/products/pdts`)
  - Frontend Angular (raíz del proyecto)
  - Archivo `docker-compose.yml` corregido en la raíz
  - Archivo `init.sql` en la raíz (si usas scripts de inicialización para MySQL)

---

## 2. Generar artefactos backend

### a) Microservicio Categorías
```sh
cd categorias
./mvnw clean package
```
Verifica que se genere el archivo `target/categorias-0.0.1-SNAPSHOT.jar`.

### b) Microservicio Productos
```sh
cd products/products/pdts
./mvnw clean package
```
Verifica que se genere el archivo `target/pdts-0.0.1-SNAPSHOT.jar`.

---

## 3. Generar build de frontend (opcional si usas tu propia imagen)
```sh
npm install
ng build --configuration production
```
Verifica que se genere la carpeta `dist/`.

---

## 4. Verificar Dockerfile

- Cada microservicio debe tener un `Dockerfile` que copie el JAR generado y exponga el puerto 8080.
- El frontend debe tener un `Dockerfile` que copie el contenido de `dist/` y lo sirva con nginx (si usas build propio).

---

## 5. Verificar y ajustar docker-compose.yml

- El archivo debe orquestar los servicios: MySQL, categorías, productos y frontend.
- El contexto de build de `products-api` debe ser `./products/products/pdts`.
- Los puertos internos de los microservicios deben ser 8080.
- El frontend debe exponerse en el puerto 80.

---

## 6. Desplegar la solución

Desde la raíz del proyecto:
```sh
docker-compose build
docker-compose up -d
```

---

## 7. Verificar servicios

- Comprueba que los contenedores estén corriendo:
```sh
docker ps
```
- Accede al frontend desde el navegador: `http://<IP-o-dominio-del-servidor>/`
- Accede a las APIs:
  - Productos: `http://<IP-o-dominio-del-servidor>:8088`
  - Categorías: `http://<IP-o-dominio-del-servidor>:8089`

---

## 8. Notas adicionales

- Si usas firewall, asegúrate de abrir los puertos 80, 8088, 8089 y 3306.
- Si necesitas reconstruir, usa:
```sh
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```
- Para ver logs de un servicio:
```sh
docker-compose logs -f <nombre-del-servicio>
```

---

¡Listo! Con estos pasos tu sistema debería estar desplegado y funcionando correctamente.
