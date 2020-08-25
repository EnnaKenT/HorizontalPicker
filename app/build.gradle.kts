plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "com.vber.horizontalpicker"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        )
    }
    buildTypes {
//        getByName("debug") {
//            isMinifyEnabled = false
//        }
        getByName("release") {
            isMinifyEnabled = true
        }
//        create("anotherbuildtype") {
//        }
    }
    kotlinOptions.apply { jvmTarget = JavaVersion.VERSION_1_8.toString() }
}

androidExtensions.isExperimental = true

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.5.0-alpha02")
    implementation("androidx.fragment:fragment-ktx:1.3.0-alpha08")
    implementation("com.google.android.material:material:1.3.0-alpha02")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0")

    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}