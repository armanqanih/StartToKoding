buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.22")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath ("com.google.firebase:perf-plugin:1.4.2")
        classpath ("com.google.gms:google-services:4.4.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
