# Weather Report API

Este proyecto es una API de reporte del clima que obtiene los datos actuales desde OpenWeatherMap y los expone a través de un servicio REST.

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- **WeatherReportController**: Controlador REST que expone un endpoint para obtener el reporte del clima en base a la latitud y longitud proporcionadas.
- **WeatherReportService**: Servicio que consume la API de OpenWeatherMap para obtener el reporte del clima y devolver los datos como temperatura y humedad.
- **WeatherApiResponse**: Clase que modela la respuesta de la API de OpenWeatherMap.
- **WeatherReport**: Clase que representa el reporte de clima con temperatura y humedad.

### Endpoints

- **GET /v1/api/weather-report**: Este endpoint recibe dos parámetros, `latitude` y `longitude`, para retornar el reporte del clima.

Ejemplo de request:

```bash
http://localhost:8080/v1/api/weather-report?latitude=37.8267&longitude=-122.4233
```

## Requisitos

- Java 11 o superior.
- Maven.
- Cuenta y API Key de OpenWeatherMap.

## Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu_usuario/weather-report-api.git
cd weather-report-api
```

### 2. Configuración de la API Key

Modifica el archivo `WeatherReportService.java` para incluir tu API Key de OpenWeatherMap:

```java
private static final String API_KEY = "TU_API_KEY";
```

### 3. Ejecutar el proyecto

Puedes ejecutar el proyecto utilizando Maven:

```bash
mvn spring-boot:run
```

El servidor estará disponible en `http://localhost:8080`.

## Pruebas Unitarias

Este proyecto incluye pruebas unitarias para los componentes `WeatherReportController` y `WeatherReportService` utilizando Mockito y JUnit. Puedes ejecutar las pruebas usando el siguiente comando:

```bash
mvn test
```

### Componentes probados:

- **WeatherReportControllerTest**: Prueba la lógica del controlador simulando el servicio `WeatherReportService`.
- **WeatherReportServiceTest**: Prueba la funcionalidad de `WeatherReportService` simulando la respuesta de la API de OpenWeatherMap.

## Ejemplos de uso

Una vez que el servidor esté en ejecución, puedes hacer solicitudes para obtener el reporte del clima usando la siguiente URL, cambiando la latitud y longitud según sea necesario:

```bash
http://localhost:8080/v1/api/weather-report?latitude=37.8267&longitude=-122.4233
```

El servidor responderá con un objeto JSON que contiene la temperatura y humedad actuales.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación.
- **Spring Boot**: Framework para construir la API REST.
- **RestTemplate**: Para realizar solicitudes HTTP a la API de OpenWeatherMap.
- **JUnit 5**: Framework para pruebas unitarias.
- **Mockito**: Framework para simular dependencias en las pruebas.

## Contribución

Si deseas contribuir al proyecto:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-feature`).
3. Realiza los cambios necesarios y commitea (`git commit -m 'Añadir nueva feature'`).
4. Haz push a la rama (`git push origin feature/nueva-feature`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la licencia MIT. Para más detalles, consulta el archivo [LICENSE](LICENSE).

---

### Autor

- **Johann Amaya**


