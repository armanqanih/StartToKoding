plugins {
    id ("com.android.test")
    id ("org.jetbrains.kotlin.android")
}

android {
    namespace  = "com.example.benchmark"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose  = false
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
        minSdk  = 24
        targetSdk  = 34

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // This benchmark buildType is used for benchmarking, and should function like your
        // release build (for example, with minification on). It"s signed with a debug key
        // for easy local/CI testing.
        create("benchmark") {
            buildConfigField("String", "BASE_URL", "\"http://185.208.79.114:8001/\"")
            buildConfigField("String", "UPLOAD_IMAGE_URL", "\"https://upload.kilid.com\"")
            buildConfigField("String", "ADVERTISEMENT_SALE_URL", "\"https://kilid.com/buy/detail/\"")
            buildConfigField("String", "ADVERTISEMENT_RENT_URL", "\"https://kilid.com/rent/detail/\"")
            buildConfigField("String", "CDN", "\"https://cdn.kilid.com/\"")
            isMinifyEnabled = false
            isShrinkResources = false
            matchingFallbacks += listOf("debug")
            proguardFiles("benchmark-rules.pro")
        }
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation ("androidx.test.ext:junit:1.1.5")
    implementation ("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.test.uiautomator:uiautomator:2.2.0")
    implementation ("androidx.benchmark:benchmark-macro-junit4:1.1.1")
    implementation ("androidx.profileinstaller:profileinstaller:1.3.1")
}


