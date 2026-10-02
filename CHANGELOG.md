# Registro de Cambios (Changelog)

Todos los cambios notables realizados en este proyecto se documentan en este archivo.

## [v1.0] - 2026-09-29

### Añadido

* Implementación de la lógica para enviar mensajes desde `SendMessageActivity` a
  `ViewMessageActivity` mediante `Intent` y `Bundle`.
* Recepción del texto en `ViewMessageActivity` y renderizado en `textView2`.
* Documentación de código con formato KDoc en todas las clases Kotlin.
* Creación de la documentación del proyecto (`README.md`, `MANUAL_USUARIO.md` y `CHANGELOG.md`).

---

## [v0.1] - 2026-09-20

### Añadido

* Configuración inicial del proyecto Android en Kotlin.
* Diseño visual de las pantallas `activity_send_message.xml` y `activity_view_message.xml` usando
  `LinearLayout`.
* Definición de recursos estáticos en `strings.xml`, `colors.xml` y `dimens.xml`.