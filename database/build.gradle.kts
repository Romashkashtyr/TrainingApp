plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.database"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(project(":core"))

    // Firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation (libs.firebase.database)
    implementation (libs.firebase.core)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.messaging)

    // Moxy
    implementation(libs.moxy)
    kapt (libs.moxy.compiler)
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.ktx)

    //Room
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    //Retrofit
    api(libs.retrofit)
    api(libs.converter.gson)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.retrofit.kotlinx.serialization)


    //Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}