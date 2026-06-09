import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

private val keystorePropertiesFile = rootProject.file("keystore.properties")
private val keystoreProperties = keystorePropertiesFile.inputStream().use { inputStream ->
    Properties().apply {
        load(inputStream)
    }
}
private val apiKey = keystoreProperties.getProperty("TRAINING_API_KEY")

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "TRAINING_API_KEY", apiKey)
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
        buildConfig = true
    }
}

dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.moxy)
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.ktx)
    kapt(libs.moxy.compiler)

    api (libs.glide)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation (libs.androidx.datastore.preferences)

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //OKHttp
    val okhttpVersion = "4.12.0"
    implementation(libs.logging.interceptor)



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}