# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep all classes in your app package
-keep class com.dusol.thelearnerscommunity.** { *; }

# Suppress warnings for the specified classes
-dontwarn org.conscrypt.Conscrypt**
-dontwarn org.conscrypt.OpenSSLProvider**
-dontwarn android.media.LoudnessCodecController$OnLoudnessCodecUpdateListener
-dontwarn android.media.LoudnessCodecController
-dontwarn com.squareup.okhttp.CipherSuite
-dontwarn com.squareup.okhttp.ConnectionSpec
-dontwarn com.squareup.okhttp.TlsVersion

# Firebase rules - Keep all Firebase classes
-keep class com.google.firebase.** { *; }
-keep class com.google.firebase.analytics.** { *; }
-keep class com.google.firebase.crashlytics.** { *; }
-keep class com.google.firebase.perf.** { *; }
-keep class com.google.firebase.messaging.** { *; }
-keep class com.google.firebase.database.** { *; }
-keep class com.google.firebase.database.GenericTypeIndicator { *; }
-keepclassmembers class com.google.firebase.database.GenericTypeIndicator {
    T getValue(com.google.firebase.database.GenericTypeIndicator);
}

# Google Play Services rules
-keep class com.google.android.gms.** { *; }
-keep class com.google.android.gms.ads.** { *; }
-keep class com.google.android.gms.internal.ads.** { *; }
-keep class com.google.android.gms.common.** { *; }
-keep class com.google.android.gms.tasks.** { *; }
-dontwarn com.google.android.gms.**
-dontwarn com.google.android.gms.ads.**
-dontwarn com.google.android.gms.internal.ads.**

# Material Design components
-keep class com.google.android.material.** { *; }
-keep class com.google.android.material.shape.** { *; }
-keep class com.google.android.material.tabs.** { *; }
-dontwarn com.google.android.material.**

# AndroidX and Support libraries
-keep class androidx.** { *; }
-keep class androidx.appcompat.** { *; }
-keep class androidx.fragment.app.** { *; }
-keep class androidx.recyclerview.widget.** { *; }
-keep class androidx.viewpager2.** { *; }
-keep class androidx.constraintlayout.** { *; }
-keep class androidx.navigation.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.gridlayout.** { *; }
-dontwarn androidx.**

# Fragment rules - Critical for Notes Store
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public <init>();
    public <init>(android.os.Bundle);
}
-keep class * extends androidx.fragment.app.Fragment {
    public <init>();
    public <init>(android.os.Bundle);
}

# RecyclerView and Adapter rules
-keep class androidx.recyclerview.widget.RecyclerView { *; }
-keep class androidx.recyclerview.widget.RecyclerView$ViewHolder { *; }
-keep class androidx.recyclerview.widget.RecyclerView$Adapter { *; }
-keepclassmembers class * extends androidx.recyclerview.widget.RecyclerView$Adapter {
    public <init>();
}

# ViewPager2 rules
-keep class androidx.viewpager2.widget.ViewPager2 { *; }
-keep class androidx.viewpager2.adapter.FragmentStateAdapter { *; }

# PDF viewer libraries
-keep class com.github.barteksc.pdfviewer.** { *; }
-keep class com.shockwave.pdfium.** { *; }

# Picasso image loading library
-keep class com.squareup.picasso.** { *; }
-dontwarn com.squareup.picasso.**

# Glide image loading library
-keep class com.github.bumptech.glide.** { *; }
-dontwarn com.github.bumptech.glide.**

# Play Store app update library
-keep class com.google.android.play.** { *; }
-dontwarn com.google.android.play.**

# Notes Store specific rules - Prevent obfuscation
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.** { *; }
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.** { *; }
-keep class com.dusol.thelearnerscommunity.ProductDetailsDialogFragment { *; }
-keep class com.dusol.thelearnerscommunity.Notes_Store { *; }
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.NotesManagerAdapter { *; }
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.VP2_PaidNotes_Adapter { *; }
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity { *; }
-keep class com.dusol.thelearnerscommunity.NotesStoreManage.NotesStore_HomePage { *; }

# Keep all Activity classes
-keep class * extends android.app.Activity { *; }
-keep class * extends androidx.appcompat.app.AppCompatActivity { *; }

# Keep all Service classes
-keep class * extends android.app.Service { *; }

# Keep all BroadcastReceiver classes
-keep class * extends android.content.BroadcastReceiver { *; }

# Keep all ContentProvider classes
-keep class * extends android.content.ContentProvider { *; }

# Keep all Application classes
-keep class * extends android.app.Application { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep R classes
-keep class **.R$* {
    public static <fields>;
}

# Keep custom views
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    public *** get*();
}

# Keep onClick methods
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep attributes for debugging
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes Deprecated

# Optimizations
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

# Don't warn about unused classes
-dontwarn **