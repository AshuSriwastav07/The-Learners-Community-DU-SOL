plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.dusol.thelearnerscommunity"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dusol.thelearnerscommunity"
        minSdk = 23
        targetSdk = 35
        versionCode = 21
        multiDexEnabled= true
        versionName = "2025.07.2.4.9" //Explain Year.month.versionName
        //This is Version Name 2.4 and we cant use same version code again

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        exclude ("androidsupportmultidexversion.txt")
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("com.google.firebase:firebase-messaging-ktx:24.1.2")
    implementation ("com.google.android.gms:play-services-ads:24.4.0")// Adds
    implementation("com.google.firebase:firebase-messaging:24.1.2")
    implementation ("com.android.support:multidex:1.0.3")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.google.firebase:firebase-analytics:22.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.navigation:navigation-fragment:2.9.1")
    implementation("androidx.navigation:navigation-ui:2.9.1")
    implementation("androidx.activity:activity:1.10.1")
    implementation("androidx.gridlayout:gridlayout:1.1.0")
    implementation("com.google.android.play:app-update:2.1.0")// For in App Update
    testImplementation("junit:junit:4.13.2")
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}
