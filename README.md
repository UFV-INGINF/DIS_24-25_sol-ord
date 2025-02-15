# DIS_24-25_sol-ord
Solución del examen práctico de la convocatoria ordinaria DIS 24-25

# Tabla de Contenidos

1. [DIS_24-25_sol-ord](#dis_24-25_sol-ord)
1. [Organización](#organización)
   1. [Organización](#organización)
   1. [Organización](#organización)
1. [Git](#git)
1. [Docker](#docker)
   1. [Docker compose](#docker-compose)

# Organización
Este proyecto consta de tres carpetas. Una para el frontend y otra para el backend, así como una para almacenar los ficheros pdf.

- Backend: **nsb**
- Frontend: **nsb_frontend**
- Ficheros pdf: **pdf-files**

En el enunciado del examen no se pide guardar los ficheros en una carpeta, sino dejarlo en la misma raíz del proyecto backend. En este caso, debido a que he contenerizado la aplicación completamente haciendo uso de Docker y Docker Compose, es necesario añadir esta carpeta.

## Frontend

El proyecto de frontend contiene todo lo solicitado en el enunciado. Se han separado las distintas vistas en ficheros independientes, que no rutas de navegación, para tener los distintos formularios independientes (aunque fueran muy similares entre sí). Debido a esto, se ha hecho uso de gestión de eventos para que el grid se actualice de forma automática cuando se añade un elemento o se edita la información de alguno de ellos.
Esto no es extrictamente necesario para la solución del ejercicio, pero esta solución así lo plantea.
El puerto en el que ejecuta el frontend es **8081**.

## Backend

El proyecto backend, igualmente, contiene los 5 endpoints que se piden en el enunciado. Como se indica anteriormente, en esta solución se ha creado la carpeta `pdf-files`, donde guardar los ficheros pdf generados en la aplicación. Esto se debe a que de cara a la contenerización de la aplicación y el uso de un volumen para poder consultar el fichero en el host, es necesario que exista esta carpeta.
El puerto en el que ejecuta el frontend es **8080**.

# Git

La gestión de Git de esta solución es muy sencilla, puesto que el código está sincronizado en ambas ramas master y develop, pero no se han añadido ramas de feature. Sí se han generado dos releases/versiones, mediante el uso de Git Flow y las instrucciones pertinentes.

- La versión 1.0.0. dispone de una versión ejecutable en local de la aplicación. Es decir, desde el propio IDE IntelliJ usado durante el curso.
- La versión 1.0.1 dispone de una versión ejecutable de forma contenerizada, mediante la ejecución del comando `docker compose up --build`. 

# Docker

La solución propuesta con Docker dispone de dos ficheros Dockerfile para cada uno de los proyectos (frontend y backend). Esto se debe a que la solución del ejercicio sólo pedía los comandos docker para realizar la ejecución de los contenedores asociados a cada una de las partes, pero no se pedía que se pudieran ejecutar ni la creación de las imágenes en sí.
Dado que la solución que os proponemos aquí sí permite la contenerización completa de la aplicación, los ficheros Dockerfile de cada uno de los proyectos se han modificado para que compile el código Java y, a continuación, genere la imagen que levantará el contenedor final.

Así, los ficheros Dockerfile de cada proyecto junto con su contenido y sus nombres, son los siguientes (los nombres aplican a ambos proyectos):

- Dockerfile: fichero que contiene la generación de la imagen completa, incluyendo el código para compilar el proyecto.
- Dockerfile_Exam: este fichero sólo contiene las líneas de generación de la imagen del proyecto, sin necesidad de la parte de compilación, puesto que no se pedía en el enunciado del ejercicio.

## Docker compose

Se incluye un fichero `docker-compose.yml` en el que se definen las instrucciones para la generación del proyecto completo. También se incluye un fichero `.env` en el que definir la ruta en vuestro equipo donde hayáis clonado esta solución, para que los ficheros pdf se generen correctamente en el equipo host mediante el uso de un volumen que apunta a esta carpeta.
En el fichero `docker-compose.yml`, en el apartado relativo al volumen, se utiliza la nomenclatura `${}`, donde se define el nombre de la variable contenida en el fichero `.env` con la ruta a la carpeta correcta.

Para ejecutar el proyecto completo, es necesario ejecutar el comando `docker compose up --build`. El flag --build, se encargará de ejecutar la construcción de las imágenes para compilar el código java de ambos proyectos, así como de generar las imágenes finales a partir de las que se generarán los contenedores.

 ⚠️ **¡Importante!** Para ejecutar el proyecto completo correctamente, es necesario definir el path hasta la ruta donde se encuentra el proyecto clonado, a la carpeta `pdf-files`

