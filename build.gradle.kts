buildscript {
    val kotlin_version by extra("1.4.32")
    repositories {
        google()
        mavenCentral()
        maven {
            url=  uri("https://www.jitpack.io")
        }
    }
    dependencies {

        classpath("com.android.tools.build:gradle:${Vers.GRADLE}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
      //  classpath ("com.google.firebase:firebase-crashlytics-gradle:2.3.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Vers.HILT}")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:${Vers.KOTLIN}")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
