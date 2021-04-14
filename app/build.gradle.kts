plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)
        // app info
        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        applicationId = "com.giuseppesorce.gsarchitecture"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
          //  isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //modules
    implementation(project(":architecture"))
    implementation(project(":domain"))
    implementation(project(":data"))


    //dipendencies
    implementation(Deps.KOTLIN_STLIB)
    implementation(Deps.coreKtx)
    implementation(Deps.appCompact)
    implementation(Deps.constraintLayout)
    implementation(Deps.UIDesign.material_design)


    //DIPENDENCY INJECTION
    //dagger
    implementation(DI.DAGGER_DEP)
    kapt(DI.DAGGER_ANNOTATION)
    // hilt
    implementation(DI.HILT_DEP)
    kapt(DI.HILT_KAPT)
    kaptAndroidTest(DI.HILT_ANDROID_KAPT_TEST)
    kaptTest(DI.HILT_KAPT_TEST)
    implementation(DI.HILT_VIEWMODEL)
    kapt(DI.HILT_COMPILER)

    // ViewModel
    implementation(Deps.ViewModel.VIEMODEL_DEP)
    // if there isn't java8 have to use  kapt "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    implementation(Deps.ViewModel.VIEMODEL_JAVA8_DEP)
    implementation(Deps.ViewModel.viewmodel_reactivestreams_dep)
    implementation(Deps.ViewModel.viewmodel_livedata_dep)
    implementation(Deps.ViewModel.viewmodel_savedstate_dep)

    //navigation
    implementation(Deps.FRAGMENT_KOTLIN)
    implementation(Deps.ACTIVITY_KTX)
    implementation(Deps.NAVIGATION_FRAG_KTX)
    implementation(Deps.navigation_ui_ktx)

    //Activity
    implementation(Deps.ACTIVITY_KTX)
    // Serialitation
    implementation(Kotlin.SERIALITATION_CORE)
    implementation(Kotlin.SERIALITATION_JSON)
    //timber
    implementation(Deps.TIMBE_DEP)

    // Coroutines
    implementation(Kotlin.COROUTINES)
    implementation(Kotlin.COROUTINES_ANDROID)


    // Retrofit
    implementation(Network.RETROFIT)
    implementation(Network.OKHTTP)
    implementation(Network.KOTLIN_SER_CONVERTER)
    // Interceptors
    implementation(Network.LOGGIG_INTERCEPTOR)
    //Coil
    implementation(Deps.UIDesign.COIL)
    //Base artifact without singleton
    implementation(Deps.UIDesign.COIL_BASE)
    //Leak Canary
    debugImplementation(Deps.KANARY_DEP)

    //Mock
    testImplementation(MockingLib.MOCKK_DEP)
    testImplementation(MockingLib.CORETESTING)
    testImplementation(MockingLib.MOCK_COROUTINES)

    //TESTING
    testImplementation(TestingLib.JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)

}