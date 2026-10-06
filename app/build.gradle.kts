plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.dokka)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.sendmessage"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.sendmessage"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dokka {
    moduleName.set("SendMessage") //Este nombre puede ser aleatorio, pero conviene que sea el del proyecto

    dokkaPublications.html {
        outputDirectory.set(rootProject.file("documentation"))
    }

    dokkaSourceSets {
        register("main") {
            //sourceRoots.from(file("src/main/java"))
            jdkVersion.set(11)
            enableAndroidDocumentationLink.set(false)
        }
    }
}


dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

}

// Configuración de rutas personalizadas para Dokka
tasks.dokkaHtml.configure {
    // Redirige el formato HTML a la raíz del proyecto
    outputDirectory.set(file("../documentation/html"))
}

tasks.dokkaJavadoc.configure {
    // Redirige el formato Javadoc a la raíz del proyecto
    outputDirectory.set(file("../documentation/javadoc"))
}