package org.lotka.xenonx.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale
import javax.inject.Inject

class SettingsManagerImpl @Inject constructor() : SettingsManager {
    override fun attachBaseContext(base: Context?) =
        updateSettings(base)

    override fun changeLanguage(context: Context?, languageCode: String?) {
//        PreferencesUtils.setLanguage(context, language)
        updateSettings(context)
    }

    override fun changeUiMode(context: Context?, uiMode: Int?) {
//        PreferencesUtils.setUiMode(context, uiMode)
        updateSettings(context)
    }

    /**
     * Method called in [attachBaseContext] to wrap original [Context] with user settings.
     * @param base original [Context] passed to our component
     * @return wrapped [Context] with user preferences or NULL if original context is NULL.
     */
    @Suppress("DEPRECATION")
    private fun updateSettings(base: Context?): Context? {
        // get stored locale from Preferences
//        val locale = PreferencesUtils.getLocale(base)
        val locale = Locale("fa", "IR")
        // set that Locale as default
        Locale.setDefault(locale)
        // get resources class of given context
        val res = base?.resources
        // create new configuration passing old configuration from original Context
        val config = Configuration(res?.configuration)
        // update config uiMode with user stored one
//        config.uiMode = PreferencesUtils.getUiMode(base)
        config.uiMode = Configuration.UI_MODE_NIGHT_NO
        // set default night mode
        setDefaultNightMode(config.uiMode)
        // finally, update locale depends on which Android version is installed and return Context
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setLocaleForApi24(config, locale)
            base?.createConfigurationContext(config)
        } else {
            config.setLocale(locale)
            base?.createConfigurationContext(config)
        }
    }

    /**
     * Method called from [updateSettings] when we set uiMode to notify our delegate to correctly
     * show Dark or Light Theme.
     * @param uiMode that is set.
     */
    private fun setDefaultNightMode(uiMode: Int?) {
        if (uiMode == Configuration.UI_MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    /**
     * Method called from [updateSettings] when we want to set [Locale] that user configured.
     * Requires Android with version N or greater.
     * @param config config file for which param [locale] will be set
     * @param locale user set Locale
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun setLocaleForApi24(
        config: Configuration,
        locale: Locale
    ) {
        val set: MutableSet<Locale> = LinkedHashSet()
        // bring the user locale to the front of the list
        set.add(locale)
        val all = LocaleList.getDefault()
        for (i in 0 until all.size()) {
            // append other locales supported by the user
            set.add(all[i])
        }
        val locales = set.toTypedArray()
        config.setLocales(LocaleList(*locales))
    }
}
