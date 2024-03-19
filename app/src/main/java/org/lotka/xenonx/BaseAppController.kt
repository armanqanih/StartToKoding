package org.lotka.xenonx

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import com.jakewharton.threetenabp.AndroidThreeTen
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltAndroidApp
class BaseAppController : Application() {


    @Inject
    lateinit var firebaseAnalytics : FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()
        Firebase()
        activateAppMetrica()
       // Mapir.init(this, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjBkNmM5NWM1ZWM1MzEyMDE4ODFlN2I5YmEzZWRmMWZmOTcxNDZmZGNiNTQwN2RkZTg2YWMxZmU4MTM0MDU3ZTYwZWU4MGJlN2I3ZDhiOTQ2In0.eyJhdWQiOiI3ODI1IiwianRpIjoiMGQ2Yzk1YzVlYzUzMTIwMTg4MWU3YjliYTNlZGYxZmY5NzE0NmZkY2I1NDA3ZGRlODZhYzFmZTgxMzQwNTdlNjBlZTgwYmU3YjdkOGI5NDYiLCJpYXQiOjE1ODA4OTE1OTYsIm5iZiI6MTU4MDg5MTU5NiwiZXhwIjoxNTgzMzk3MTk2LCJzdWIiOiIiLCJzY29wZXMiOlsiYmFzaWMiXX0.LejIPORWXNBerGtGLJiiXhaWYSVqWfgwrzsVhN2L6uB0JTfw-btQ9UxxtJlfcGtopVzlCjKp2WiQdvSDqItC2xD_rE_e36eeuR3vq7c6d8Ipa9MQdgC7xeCFCwkLkaMh4A1KVVDuX4QdY_A0kexLhLboO0tNVI3VoiBjuIW63lEYPAwbfAQfVpF8KM1KlT7n58xO_rgqesmiTE6Gtvq8gb58pyEcH_HARCO6Ue8y98Mc3_vsVKX_O4-y6kYWSVaD62xvLC3c646Xup-TMJ-atsFy5lBGco8zKirJW3eTgL-yxe_rKM3lirpU2GOsiWFA3-ApAxQJbEkTHSFyON2cHg");
        AndroidThreeTen.init(this)
        // init Timber
        if (org.lotka.xenonx.BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }


    private fun Firebase() {
        FirebaseApp.initializeApp(this)
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        val bundle = Bundle()
        val random = Random.nextInt(0, 1000000)
        bundle.putInt("random_number", random) // Using the generated random number
        firebaseAnalytics.logEvent("AndroidAppOpened", bundle)
        val random2 = Random.nextInt(0, 1000000)
        bundle.remove("random_number")
        bundle.putInt("random_number", random2) // Using the generated random number
        firebaseAnalytics.logEvent("AndroidAppOpened", bundle)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isComplete) {
            } else if ((it.isCanceled)) {
                Toast.makeText(this, "خطای داخلی رخ داده است کد ۳۵۴۳۲", Toast.LENGTH_LONG).show()
                Timber.d("FirebaseMessaging.getInstance().token.addOnCompleteListener isCanceled")
            }
        }
    }

    private fun activateAppMetrica() {
        val versionName: String =
            this.packageManager.getPackageInfo(this.packageName, 0).versionName
        val appMetricaConfig: YandexMetricaConfig =
            YandexMetricaConfig.newConfigBuilder("fa0d2076-f570-4e1e-a941-2d24b34d8d62")
                .withLocationTracking(true)
                .withAppVersion(versionName)
                .withLogs()
                .withStatisticsSending(true)
                .withCrashReporting(true)
                .withNativeCrashReporting(true)
                .build()
        YandexMetrica.activate(applicationContext, appMetricaConfig)
        YandexMetrica.enableActivityAutoTracking(this)
    }
}