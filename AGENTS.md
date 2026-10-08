# AGENTS.md

Proyecto Android de clase (2º DAM – Desarrollo de Interfaces): app de dos actividades que envía un texto mediante `Bundle`/`Intent`. Toda la documentación, comentarios y nombres de este repo están **en español**: redacta cualquier cambio documental en español.

## Comandos exactos

Windows no tiene `./gradlew` ejecutable fuera de Android Studio; usa `gradlew.bat` (o `./gradlew` en bash/CI):

* Compilar debug: `gradlew.bat assembleDebug`
* Tests unitarios: `gradlew.bat test` (una clase: `gradlew.bat test --tests "com.example.sendmessage.ExampleUnitTest"`)
* Tests instrumentados: `gradlew.bat connectedAndroidTest` (requiere emulador o dispositivo conectado)
* Estáticos: `gradlew.bat lint` — **no hay ktlint/detekt/spotless** configurados
* Documentación: `gradlew.bat dokkaGenerate` → escribe en `documentation/`
* Validar README tras tocarlo: `python .opencode/skills/personalice-docs-generator/scripts/validate_readme.py README.md`

Orden recomendado de verificación: `assembleDebug` → `test` → `lint`.

## Estructura real (no todo lo que se ve se usa)

* Módulo único `:app`, paquete `com.example.sendmessage`.
* Flujo real: `SendMessageActivity` (launcher) → `ViewMessageActivity`, pasando el mensaje con un `Bundle` y la clave `KEY_MESSAGE`. No hay Navigation Component en ejecución.
* Existen dependencias `androidx.navigation` y `app/src/main/res/navigation/nav_graph.xml`, pero **no se usan** (cero referencias a `NavController`); restos de la plantilla. No los actives sin necesidad.
* `model/Message.kt` y `model/Person.kt` usan `@Parcelize` (plugin parcelize aplicado en `app/build.gradle.kts`).
* `SendMessageApplication` es la clase `Application` declarada en el manifiesto.
* `viewBinding = true`; layouts en `res/layout/`, localización por defecto en español (`values/`) con traducción en `values-en/`.

## peculiaridades de build (AGP 9 / Gradle 9)

* AGP **9.3.3**, Gradle wrapper **9.5.0**, Dokka 2.2.0; versiones solo en `gradle/libs.versions.toml` (version catalog) — no escribas versiones sueltas en `build.gradle.kts`.
* **No hay plugin `kotlin-android`**: AGP 9 integra Kotlin por sí mismo. No lo añadas.
* DSL nuevo de AGP 9 en `app/build.gradle.kts`: `compileSdk { version = release(37) { minorApiLevel = 1 } }`; `targetSdk = 36`, `minSdk = 24`. `compileOptions` fija Java 11 solo como *target*.
* `org.gradle.configuration-cache=true` está activo: tareas o scripts que rompan la configuration cache fallan en la 2.ª ejecución.
* Reglas R8 personalizadas en `app/src/main/keepRules/rules.keep` (AGP las combina automáticamente con `proguard-rules.pro` si lo hubiera).
* `local.properties` (ruta del SDK) es local y está en `.gitignore`: en un clon nuevo Android Studio lo regenera; sin él `assembleDebug` falla.
* `settings.gradle.kts` usa `FAIL_ON_PROJECT_REPOS`: no declares repositorios dentro de módulos.

## CI / documentación generada

* `.github/workflows/desplegar-dokka.yml`: en cada **push a `main`** ejecuta `./gradlew dokkaGenerate` con JDK 17 y publica `documentation/` en GitHub Pages (crea `documentation/.nojekyll`).
* `documentation/` está versionado en git: si modificas KDoc, el cambio local queda desincronizado hasta que CI lo regenere en `main`.
* `gradlew.bat dokkaHtml` y `dokkaJavadoc` redirigen salida a `documentation/html` y `documentation/javadoc` (config adicional en `app/build.gradle.kts`).

## Skills de OpenCode en este repo

* `.opencode/skills/personalice-docs-generator/`: exigida al crear/modificar `README.md` o documentar Kotlin (KDoc). Reglas clave: un único `#` de título y **validar siempre con el script** `validate_readme.py` (Python requerido).
* `.opencode/skills/generar-fichero-license/`: genera el fichero `LICENSE` (mayúsculas, sin extensión) en la raíz.

## Errores conocidos que no "arregles" por tu cuenta sin avisar

* `README.md` referencia `screenshots/logcat_evidencia.png` y `screenshots/data_data_explorer.png`, pero **no existe la carpeta `screenshots/`** (las capturas válidas están en `images/`): imágenes rotas ya presentes.
* `README.md` duplica la tabla de capturas de la sección 1 (mismo bloque dos veces).
* `app/release/` (APK local `app-release.apk`) está sin `.gitignore` y sin versionar: no lo subas.
* Trabajo sin commitear en curso sobre layouts y `strings.xml`; rama actual `main`. No hagas `git checkout`/`reset` sobre cambios del usuario.
