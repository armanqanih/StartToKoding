package org.lotka.xenonx.util

import android.content.Context

interface SettingsManager {

    /**
     * Method called in [Application.attachBaseContext] and [BaseActivity.attachBaseContext]
     * to wrap original [Context] with user preferences for [Locale] and [Configuration.uiMode].
     * @param base original Context passed to our component
     * @return changed Context with user [Locale] and [Configuration.uiMode]
     */
    fun attachBaseContext(base: Context?): Context?

    /**
     * Method called when user changed app [Locale].
     * @param context of a component that called this method
     * @param languageCode ISO 639 language code that will be set
     */
    fun changeLanguage(context: Context?, languageCode: String?)

    /**
     * Method called when user changed [Configuration.uiMode].
     * @param context of a component that called this method
     * @param uiMode that will be set. It can be one of following:
     * {[Configuration.UI_MODE_NIGHT_NO] or [Configuration.UI_MODE_NIGHT_YES]}
     */
    fun changeUiMode(context: Context?, uiMode: Int?)
}
