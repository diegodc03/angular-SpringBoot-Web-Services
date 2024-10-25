# Aplicación de Gestión Empresarial

Esta aplicación está diseñada para gestionar de manera integral el flujo de trabajo y las operaciones de ventas de una empresa, proporcionando un sistema confiable y seguro para la administración de inventarios, ventas, autenticación de usuarios y registro de horas laborales. La arquitectura está basada en **Spring Boot** para el backend y **Angular** para el frontend, lo que permite una experiencia ágil y eficiente para administradores y empleados, además de garantizar la seguridad en el acceso mediante autenticación y autorización basada en roles.

## Características Principales

La aplicación se organiza en varias secciones, cada una enfocada en un aspecto clave de la gestión empresarial. A continuación, se detallan las funcionalidades de cada módulo:

### 1. Autenticación y Autorización

Este módulo ofrece una interfaz segura para que los usuarios puedan autenticarse y gestionar sus credenciales. Implementa autenticación mediante **JWT (JSON Web Tokens)**, lo que permite mantener sesiones activas sin almacenar información sensible en el cliente. Además, los usuarios tienen roles (como `ADMIN`, `USER`, etc.) que limitan o expanden su acceso según sus permisos.

#### Funcionalidades de Autenticación
- **Inicio de Sesión:** Los usuarios existentes pueden acceder a la plataforma introduciendo sus credenciales (correo electrónico y contraseña). Si las credenciales son válidas, el sistema emite un token JWT para gestionar la sesión.
- **Registro de Nuevos Usuarios:** Los nuevos usuarios pueden crear una cuenta ingresando información personal y sus credenciales, con validación de los datos de entrada.
- **Recuperación de Contraseña:** Los usuarios pueden solicitar instrucciones para restablecer su contraseña en caso de olvido. El sistema envía automáticamente un correo electrónico a la dirección registrada.
- **Control de Acceso:** Con **Spring Security**, solo los usuarios autenticados pueden acceder a áreas sensibles de la aplicación. Además, los roles asignados determinan el acceso a distintas funcionalidades.

### 2. Inventario y Ventas

Este módulo permite a los administradores y empleados autorizados gestionar los productos y realizar un seguimiento detallado de las ventas en tiempo real, optimizando el control de stock y mejorando la visibilidad de las operaciones comerciales de la empresa.

#### Funcionalidades de Inventario y Ventas
- **Visualización del Inventario:** Los usuarios pueden ver una lista de productos disponibles en inventario, con información detallada como nombre, cantidad en stock, precio y categoría.
- **Gestión de Productos:** Los administradores pueden agregar, actualizar o eliminar productos, lo que permite mantener la base de datos del inventario actualizada. Esto asegura que los usuarios tengan acceso a información precisa del stock.
- **Registro de Ventas:** Se facilita el registro de ventas, capturando información como datos del cliente, productos vendidos y el total de cada transacción.

### 3. Registro de Horas de Trabajo

El módulo de registro de horas permite a los empleados ingresar y registrar sus horas de trabajo diarias, almacenando esta información para consultas futuras. Los administradores pueden acceder a estos registros para monitorear y gestionar el cumplimiento de los horarios laborales, ayudando a optimizar la productividad.

#### Funcionalidades de Horas de Trabajo
- **Registro de Horas:** Los empleados pueden registrar sus horas de entrada y salida, generando un historial detallado de asistencia que se guarda en la base de datos.
- **Resumen de Horas Trabajadas:** Los empleados pueden revisar las horas trabajadas en un periodo específico, ideal para revisión y control personal.
- **Reportes de Asistencia:** Los administradores pueden generar reportes para analizar las horas trabajadas por el personal, facilitando la gestión de horarios y asistencia.

### 4. Clasificación de Deportes (Pádel y Tenis)

El módulo de **Deportes** permite gestionar y visualizar clasificaciones para las disciplinas de pádel y tenis, facilitando el seguimiento del rendimiento de los jugadores y organizando los resultados de los partidos. Esta sección es ideal para empresas o clubes deportivos que desean llevar un registro actualizado y transparente de sus competiciones.

#### Funcionalidades de Clasificación en Pádel y Tenis

- **Registro de Partidos**: Permite ingresar los resultados de los partidos, especificando detalles como los nombres de los jugadores, la fecha del encuentro y el puntaje final. Esto garantiza que la clasificación refleje los últimos resultados de manera precisa.
- **Clasificación por Ranking**: Calcula y muestra el ranking de los jugadores basado en sus resultados de partidos recientes. Cada victoria, derrota y puntaje contribuye a la posición en la tabla de clasificación, brindando un sistema de puntos claro y competitivo.
- **Visualización de Estadísticas**: Los usuarios pueden ver estadísticas detalladas de cada jugador, como el número de partidos jugados, partidos ganados, porcentaje de victorias y puntuación general, lo cual facilita el análisis de rendimiento.
- **Historial de Enfrentamientos**: Permite a los usuarios ver el historial de enfrentamientos entre jugadores específicos, brindando un análisis más profundo de sus enfrentamientos previos y su evolución en el ranking.
- **Reportes de Temporada**: Genera reportes detallados al final de cada temporada o período de competición, destacando a los jugadores con mejor rendimiento y proporcionando información valiosa para la organización de futuras competiciones.

Este módulo contribuye a fomentar la competencia sana entre los jugadores y permite a los administradores y jugadores llevar un control claro de su desempeño.


## Tecnologías Usadas

- **Backend**: Spring Boot
- **Frontend**: Angular
- **Base de Datos**: MySQL
- **Autenticación**: Spring Security y JWT

## Despliegue en Producción

Para desplegar la aplicación en un entorno de producción, recomendamos el uso de **Docker** y **Docker Compose** para evitar problemas de versiones y facilitar la configuración de los contenedores. Los servicios básicos incluyen:

- **Backend**: Contenedor con la aplicación de Spring Boot
- **Frontend**: Contenedor con la aplicación de Angular
- **Base de Datos**: Contenedor MySQL

### Pasos para el Despliegue

1. **Configurar Docker y Docker Compose** en el servidor.
2. **Crear archivos Dockerfile** para el backend y el frontend.
3. **Configurar `docker-compose.yml`** para definir los contenedores.
4. **Subir los archivos al servidor** y ejecutar `docker-compose up -d` para iniciar los contenedores.
5. **Configurar los puertos necesarios** y asegurar el servidor con firewall y certificados SSL/TLS.

## Seguridad

Para garantizar la seguridad de la aplicación y sus datos:

- **Roles y Permisos**: Los usuarios tienen permisos de acceso definidos por roles.
- **JWT para Sesiones**: Las sesiones se gestionan con tokens JWT para mayor seguridad.
- **Protección de Endpoints**: Solo usuarios autenticados y con el rol adecuado pueden acceder a las rutas específicas de la aplicación.

## Contacto

Para cualquier consulta o información adicional sobre la aplicación, por favor contacta a:

- **Email**: [(diegodecastro@usal.es]

