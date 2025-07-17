plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.catbreedsapp"
    compileSdk = 36

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }


    defaultConfig {
        applicationId = "com.example.catbreedsapp"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
            exclude(group = "com.intellij", module = "annotations")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

// Retrofit + Moshi
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

// Coil - imagens
    implementation(libs.coil.compose)

// Jetpack Navigation Compose
    implementation(libs.androidx.navigation.compose)

// Room
    implementation(libs.androidx.room.runtime)
    //implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler.ksp)

// Coroutines
    implementation(libs.kotlinx.coroutines.android)

// ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

// Unit Testing
    testImplementation(libs.junit)

    //kotlin Json Adapter Factory
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

}