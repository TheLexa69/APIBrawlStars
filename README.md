# Aplicación API Brawl Stars

Esta es una aplicación JavaFX que interactúa con la API de Brawl Stars para obtener y mostrar información de jugadores y clubes. La aplicación utiliza una base de datos MySQL para almacenar los datos obtenidos.

## Características

- Inicio de sesión y registro de usuarios
- Obtener y mostrar información de jugadores
- Obtener y mostrar información de clubes
- Obtener y mostrar registros de batallas
- Buscar jugadores por etiqueta
- Mostrar estadísticas de jugadores

## Tecnologías Utilizadas

- Java
- JavaFX
- MySQL
- Maven

## Requisitos Previos

- Java 11 o superior
- Maven
- MySQL

## Configuración

1. Clona el repositorio:
    ```sh
    git clone https://github.com/yourusername/apibrawlstars.git
    cd apibrawlstars
    ```

2. Configura la base de datos MySQL:
    - Crea una base de datos llamada `brawlstars`.
    - Ejecuta el script SQL `bd_brawl.sql` para crear las tablas necesarias e insertar datos iniciales.

3. Configura la conexión a la base de datos:
    - Actualiza los detalles de la conexión a la base de datos en la clase `SQLCommands`.

4. Construye el proyecto usando Maven:
    ```sh
    mvn clean install
    ```

5. Ejecuta la aplicación:
    ```sh
    mvn javafx:run
    ```

## Uso

- **Iniciar Sesión**: Ingresa tu nombre de usuario y contraseña para iniciar sesión.
- **Registrarse**: Haz clic en el botón "Registrarse" para abrir el formulario de registro y crear una nueva cuenta.
- **Buscar Jugador**: Ingresa una etiqueta de jugador y haz clic en "Buscar" para obtener y mostrar información del jugador.
- **Ver Clubes**: Haz clic en el menú "Clubes" para ver información de los clubes.
- **Ver Eventos**: Haz clic en el menú "Eventos" para ver información de los eventos.

## Estructura del Proyecto

- `src/main/java/com/example/apibrawlstars`: Contiene el código principal de la aplicación.
- `src/main/resources/com/example/apibrawlstars`: Contiene los archivos FXML para la interfaz de usuario.
- `bd_brawl.sql`: Script SQL para configurar la base de datos.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.