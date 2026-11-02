# EcoParque – Versión por consola

Versión por consola del **Sistema de gestión del Ecoparque**, hecha en Java puro. Trabajo práctico final de **Programación II** – INSPT-UTN, docente Miguel Silva.

**Autora:** Haydee (trabajo individual).

Es independiente del backend y de las pantallas web: guarda sus datos en archivos `.dat` mediante **serialización de objetos**.

## Repositorios del proyecto

| Parte | Repositorio |
|---|---|
| Backend | https://github.com/Haydee26/ecoparque-backend |
| Frontend principal (Vaadin) | https://github.com/Haydee26/ecoparque-frontend-vaadin |
| Frontend secundario (JSP) | https://github.com/Haydee26/ecoparque-frontend-jsp |
| Versión por consola (este) | https://github.com/Haydee26/ecoparque-consola |

## Qué ofrece

Inicio de sesión y menú según el rol:

| Rol | Opciones |
|---|---|
| **Administrador** | Listar y cargar zonas, hábitats, especies, itinerarios, guías y cuidadores; asignar guía a itinerario; asignar cuidador a especie; listar empleados por antigüedad |
| **Guía** | Ver mis datos y mis itinerarios |
| **Cuidador** | Ver mis datos y las especies a mi cargo |

## Tecnologías

Java puro (sin frameworks). Datos guardados con `Serializable`, `ObjectOutputStream` y `ObjectInputStream`.

## Arquitectura

Por capas: `model` (entidades) → `dao` (persistencia) → `service` (reglas) → `controller` → `view` (menús por consola).

La persistencia se define con una **interfaz DAO** que tiene dos implementaciones:

- `DAOTexto`: guarda en archivos de texto.
- `DAOSerializado`: guarda los objetos completos en archivos `.dat` (los objetos relacionados se guardan junto con el objeto).

Para cambiar de una a otra solo se modifica la clase `Principal`; el resto del programa no se toca.

## Cómo ponerlo en marcha

1. Abrir el proyecto en NetBeans (o compilar con `javac`).
2. Ejecutar la clase `ecoparque.Principal`.
3. Ingresar con un usuario de prueba. Al guardar datos se crean solos los archivos `.dat` en la carpeta del proyecto.

## Usuarios de prueba

| Usuario | Clave | Rol |
|---|---|---|
| admin | 1234 | Administrador |
| laura | 1111 | Guía |
| mario | 2222 | Cuidador |
