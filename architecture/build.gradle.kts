plugins {
    id ("com.android.library")
    kotlin("android")
    id ("dagger.hilt.android.plugin")
    id  ("kotlin-kapt")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)
    buildToolsVersion("30.0.3")

    defaultConfig {

        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

   packagingOptions {
       exclude ("classes.dex")
       exclude ( "**.**")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Deps.KOTLIN_STLIB)
    implementation(Deps.coreKtx)
    implementation(Deps.appCompact)
    implementation(Deps.constraintLayout)
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
    //Material Design
    implementation(Deps.UIDesign.material_design)

    //navigation
    implementation (Deps.NAVIGATION_FRAG_KTX)
    implementation (Deps.navigation_ui_ktx)

    // Coroutines
    implementation(Kotlin.COROUTINES)
    implementation(Kotlin.COROUTINES_ANDROID)
//    implementation(Kotlin.COROUTINES_KOTLIN_X)

    // ViewModel
    implementation(Deps.ViewModel.VIEMODEL_DEP)
    // if there isn't java8 have to use  kapt "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    implementation(Deps.ViewModel.VIEMODEL_JAVA8_DEP)
    implementation(Deps.ViewModel.viewmodel_extension)
    implementation(Deps.ViewModel.viewmodel_reactivestreams_dep)
    implementation(Deps.ViewModel.viewmodel_livedata_dep)
    implementation(Deps.ViewModel.viewmodel_savedstate_dep)
    implementation(Deps.ViewModel.lifecycle_runtime_ktx)

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