# 🚀 Proyecto: API REST de Categorías

Este proyecto es una API REST desarrollada en Java con Spring Boot 3 (JDK 17) para gestionar un catálogo de categorías, persistiendo datos en MySQL (la base se llama `test` 🗄️) y ejecutándose completamente en contenedores Docker 🐳.

---

## 🛠️ ¿Cómo se hizo el proyecto?

1. **Inicialización:** ⚙️
   - Se creó un proyecto Spring Boot 3 usando Maven y JDK 17.
   - Se implementó el modelo `Categoria` con validaciones y persistencia en MySQL.
   - Se desarrollaron los controladores y servicios siguiendo buenas prácticas REST.
   - Se configuró la conexión a MySQL en `application.properties` y en Docker Compose.

2. **Contenerización:** 📦
   - Se creó un `Dockerfile` para compilar y empaquetar la app en una imagen Java.
   - Se creó un archivo `docker-compose.yml` para levantar la app y la base de datos MySQL en contenedores.

3. **Pruebas locales:** 🧪
   - Se probó la API localmente usando Docker Compose y herramientas como curl y Postman.

---

## 🐳 ¿Cómo se creó la imagen Docker?

1. Compila el proyecto localmente (opcional):
   ```sh
   mvn clean package
   ```

2. Construye la imagen Docker:
   ```sh
   docker build -t ryuzakizeitan/categorias:v1 .
   ```

3. Sube la imagen a Docker Hub:
   ```sh
   docker login
   docker push ryuzakizeitan/categorias:v1
   ```

---

## ⬇️🐳 ¿Cómo descargar y ejecutar los contenedores desde Docker Hub?

1. Descarga la imagen desde Docker Hub:
   ```sh
   docker pull ryuzakizeitan/categorias:v1
   ```

2. Crea un archivo `docker-compose.yml` similar al siguiente:
   ```yaml
   version: '3.8'
   services:
     db:
       image: mysql:8.0
       environment:
         MYSQL_DATABASE: test
         MYSQL_ROOT_PASSWORD: root
       ports:
         - "3306:3306"
       volumes:
         - db_data:/var/lib/mysql
     app:
       image: ryuzakizeitan/categorias:v1
       ports:
         - "8089:8089"
       depends_on:
         - db
       environment:
         SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
         SPRING_DATASOURCE_USERNAME: root
         SPRING_DATASOURCE_PASSWORD: root
       restart: on-failure
   volumes:
     db_data:
   ```

3. Ejecuta los contenedores:
   ```sh
   docker compose up
   ```

🔗 La API estará disponible en:
```
http://localhost:8089/api/categorias
```

---

## 🔗 Endpoints principales
- `POST /api/categorias` — Crear categoría ✨
- `GET /api/categorias` — Listar categorías 📋
- `GET /api/categorias/{id}` — Buscar por id 🔍
- `PUT /api/categorias/{id}` — Actualizar 📝
- `DELETE /api/categorias/{id}` — Eliminar 🗑️

---

## 🗄️ Modelo de datos
- id: Long (autogenerado, PK)
- nombre: String (obligatorio, único, 3-50)
- descripcion: String (opcional, máx 255)
- fechaCreacion: LocalDateTime (asignada automáticamente)
- base de datos: `test` 🗄️

---

## 🧰 Comandos útiles
- Ver contenedores activos:
  ```sh
  docker ps
  ```
- Ver logs de la app:
  ```sh
  docker logs <nombre_contenedor_app>
  ```
- Acceder a la base de datos MySQL:
  ```sh
  docker exec -it <nombre_contenedor_db> mysql -u root -p
  ```
- Detener y eliminar contenedores:
  ```sh
  docker compose down
  ```

---

## 🐙 Repositorio de Docker Hub
[Docker Hub: ryuzakizeitan/categorias](https://hub.docker.com/r/ryuzakizeitan/categorias)

---

## 📦 Comandos esenciales para correr la app

### 1. Compilar el proyecto (opcional)
```sh
mvn clean package
```

### 2. Construir la imagen Docker
```sh
docker build -t ryuzakizeitan/categorias:v1 .
```

### 3. Ejecutar la app y la base de datos con Docker Compose
```sh
docker compose up --build
```

### 4. Descargar la imagen desde Docker Hub y correr con Docker Compose
```sh
docker pull ryuzakizeitan/categorias:v1
# Ejecuta los contenedores

docker compose up
```

### 5. Verificar que la app está corriendo
```sh
docker ps
```

### 6. Ver logs de la app
```sh
docker logs <nombre_contenedor_app>
```

### 7. Acceder a la base de datos MySQL
```sh
docker exec -it <nombre_contenedor_db> mysql -u root -p
```

### 8. Detener y eliminar contenedores
```sh
docker compose down
```

### 9. Ejemplos de uso de la API (curl)

**Crear categoría:**
```sh
curl -X POST http://localhost:8089/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ejemplo","descripcion":"Categoría de ejemplo"}'
```

**Listar categorías:**
```sh
curl http://localhost:8089/api/categorias
```

**Buscar por id:**
```sh
curl http://localhost:8089/api/categorias/1
```

**Actualizar categoría:**
```sh
curl -X PUT http://localhost:8089/api/categorias/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"NuevoNombre","descripcion":"Actualizada"}'
```

**Eliminar categoría:**
```sh
curl -X DELETE http://localhost:8089/api/categorias/1
```

---

## 🐙 Repositorio de Docker Hub
[Docker Hub: ryuzakizeitan/categorias](https://hub.docker.com/r/ryuzakizeitan/categorias)

## 🗄️ Modelo de datos
- id: Long (autogenerado, PK)
- nombre: String (obligatorio, único, 3-50)
- descripcion: String (opcional, máx 255)
- fechaCreacion: LocalDateTime (asignada automáticamente)
- base de datos: `test` 🗄️

---

## 🐬 Comando para correr MySQL 8 en Docker

Para iniciar un contenedor de MySQL 8 manualmente:
```sh
docker run --name api-categorias -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -p 3306:3306 -d mysql:8.0
```
