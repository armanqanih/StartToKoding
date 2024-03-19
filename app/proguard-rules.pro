    ####################################################################################################
    # GSON
    -keepclassmembers,allowobfuscation class * {
      @com.google.gson.annotations.SerializedName <fields>;
    }

    # Enum
    -keepclassmembers enum * { *; }
    ####################################################################################################
    # Parcelize
    -keepnames class * implements android.os.Parcelable

    -keepclassmembers class * implements android.os.Parcelable {
        public static final ** CREATOR;
    }

    -keepnames class * implements android.os.Parcelable {
        public static final ** CREATOR;
    }
    -keep public class * extends android.app.Activity
    -keep public class * extends android.app.Application
    -keep public class * extends android.app.Service
    -keep public class * extends android.content.BroadcastReceiver
    -keep public class * extends android.content.ContentProvider
    -keep public class * extends android.app.backup.BackupAgentHelper
    -keep public class * extends android.preference.Preference

    -keepclasseswithmembernames class * {
        native <methods>;
    }

    -keepclassmembers class **.R$* {
        public static <fields>;
    }

    -dontwarn com.google.protobuf.java_com_google_android_gmscore_sdk_target_granule__proguard_group_gtm_N1281923064GeneratedExtensionRegistryLite$Loader
    -dontwarn org.bouncycastle.jsse.BCSSLParameters
    -dontwarn org.bouncycastle.jsse.BCSSLSocket
    -dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
    -dontwarn org.conscrypt.Conscrypt$Version
    -dontwarn org.conscrypt.Conscrypt
    -dontwarn org.conscrypt.ConscryptHostnameVerifier
    -dontwarn org.openjsse.javax.net.ssl.SSLParameters
    -dontwarn org.openjsse.javax.net.ssl.SSLSocket
    -dontwarn org.openjsse.net.ssl.OpenJSSE


    -keep class * extends androidx.lifecycle.ViewModel { *; }


    -keep class org.lotka.xenonx.data.model** { *; }
    -keep class org.lotka.xenonx.domain.model** { *; }



    -keepattributes Signature
    -keepattributes *Annotation*
    -keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, RuntimeInvisibleParameterAnnotations
    -keepattributes EnclosingMethod

    -keep class retrofit2.** { *; }
    -keep interface retrofit2.** { *; }
    -keepattributes Exceptions

    # Gson specific classes
    -keep class com.google.gson.stream.** { *; }

    # Application classes that will be serialized/deserialized over Gson

    # If using Gson TypeToken's
    -keep class com.google.gson.reflect.TypeToken { *; }

    # Prevent proguard from stripping interface information from TypeAdapterFactory,
    # JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
    -keep class * implements com.google.gson.TypeAdapterFactory
    -keep class * implements com.google.gson.JsonSerializer


    -keep public class org.slf4j.** { *; }
    -keep public class ch.qos.** { *; }
    -keep public class org.apache.** { *; }
    -keep class com.ibm.hrl.datacapArfl.ArActivity { *; }
    -keep class com.ibm.ecm.capture.** { *; }
    -keep class com.googlecode.tesseract.android.** { *; }
    -keep class com.fiberlink.** { *; }
    -keep class com.ibm.androidsampleapplication.model.**{*;}

    -keep class org.xmlpull.v1.** { *; }

    -keep class com.android.volley.** { *; }
    -keep class org.apache.commons.logging.**

    -keepattributes *Annotation*

    -dontwarn org.apache.**

    -dontwarn com.squareup.picasso.**
    -dontwarn butterknife.internal.**
    -dontwarn org.apache.**
    -dontwarn com.ning.http.**
    -dontwarn ch.qos.logback.**
    -dontwarn org.bouncycastle.**
    -dontwarn org.apache.http.impl.auth.**
    -dontwarn com.fiberlink.maas360sdk.**
    -dontwarn com.fiberlink.maas360.**
    -dontwarn com.ibm.ecm.navigator.mdm.**
    -dontwarn com.fasterxml.jackson.databind.**
    -dontwarn android.net.http.**
    -dontwarn javax.**
    -dontwarn lombok.**
    -dontwarn org.apache.**
    -dontwarn com.squareup.**
    -dontwarn com.sun.**
    -dontwarn **retrofit**
    -dontwarn **okio**
    -dontwarn com.ibm.**

    -dontwarn org.xmlpull.v1.**
    -keep class retrofit.** { *; }
    #-keep class resources.**{*;}
    #-keep class sources.** {*;}
    -keepclasseswithmembers class * {
        @retrofit.http.* <methods>;
        }


# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response
    ####################################################################################################