import java.util.Properties
import java.io.FileInputStream

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
        versionCode = 25
        multiDexEnabled= true
        versionName = "2026.06.2.6.2" //Explain Year.month.versionName
        //Last version was  24 (2025.11.2.5.2)
        //This is Version Name 2.4 and we cant use same version code again

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ── Securely inject API keys from local.properties into BuildConfig ──
        val localProps = Properties()
        val localPropsFile = rootProject.file("local.properties")
        if (localPropsFile.exists()) {
            localPropsFile.inputStream().use { localProps.load(it) }
        } else {
            localProps["YOUTUBE_API_KEY"] = System.getenv("YOUTUBE_API_KEY") ?: ""
        }
        buildConfigField("String", "YOUTUBE_API_KEY", "\"${localProps.getProperty("YOUTUBE_API_KEY") ?: ""}\"")
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            ndk {
                debugSymbolLevel = "FULL"
            }

        }
        
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }

    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
    
    packaging {
        resources {
            excludes.add("androidsupportmultidexversion.txt")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/NOTICE.txt")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/ASL2.0")
            excludes.add("META-INF/*.kotlin_module")
        }
    }
    
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation("com.github.mhiew:android-pdf-viewer:3.2.0-beta.3")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("com.google.firebase:firebase-messaging:25.0.0") // ⚠️ Choose only one
    implementation("com.google.android.gms:play-services-ads:24.5.0")
    implementation("com.android.support:multidex:1.0.3")
    implementation("com.google.firebase:firebase-database:22.0.0") // ⚠️ Choose only one
    implementation("com.google.firebase:firebase-analytics:23.0.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.3")
    implementation("androidx.activity:activity:1.10.1") // ⚠️ Choose only one
    implementation("androidx.gridlayout:gridlayout:1.1.0")
    implementation("com.google.android.play:app-update:2.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")


}
