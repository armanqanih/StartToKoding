package org.lotka.xenonx.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.ACCESS_TOKEN
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.AGENCY_LOGO
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.BEFORE_LOGGED_IN
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.EXPIRATION_TIME
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.FINGERPRINT
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.NEW_AD_HOME_ADD_IMAGE
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.ORGANIZATION
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.PHONE_NUMBER
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.POSTPONE_UPDATE_TIME
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.REFRESH_TOKEN
import org.lotka.xenonx.di.DataStoreManager.PreferenceKeys.USER_ROLE
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "kilid_pro_dataStore"
)

class DataStoreManager @Inject constructor(@ApplicationContext val context: Context) {

    val accessTokenFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }

    val refreshTokenFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN] ?: ""
    }

    val expirationTimeFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[EXPIRATION_TIME] ?: ""
    }

    val agencyLogoFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[AGENCY_LOGO] ?: "null"
    }
    val beforeLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[BEFORE_LOGGED_IN] ?: false
    }

    val fingerPrintFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[FINGERPRINT] ?: false
    }

    val phoneNumberFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PHONE_NUMBER] ?: ""
    }

    val organizationFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ORGANIZATION] ?: ""
    }

    val userRoleFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_ROLE] ?: ""
    }





    val isForceAppUpdate: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NEW_AD_HOME_ADD_IMAGE] ?: ""
    }
    val postponeUpdateFlow: Flow<Long?> = context.dataStore.data.map { preferences ->
        preferences[POSTPONE_UPDATE_TIME] ?: 0L
    }

    suspend fun <T> updateData2(key: Preferences.Key<T>, value: T): Boolean {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    context.dataStore.edit { preferences ->
                        preferences[key] = value
                    }
                    continuation.resume(true) // Data saved successfully
                } catch (e: Exception) {
                    continuation.resume(false) // Failed to save data
                }
            }
        }
    }




    //This is Old Version of updateData
    //TODO: Remove this function in the future
    suspend fun <T> updateData(key: Preferences.Key<T>, value: T) =
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }

    suspend fun <T> deleteData(key: Preferences.Key<T>) {
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clearDataStore() =
        context.dataStore.edit {
            it.clear()
        }


    object PreferenceKeys {
        val AGENCY_LOGO = stringPreferencesKey(name = "phone_number")
        val POSTPONE_UPDATE_TIME = longPreferencesKey(name = "postpone_update_time")



        val PHONE_NUMBER = stringPreferencesKey(name = "phone_number")
        val FINGERPRINT = booleanPreferencesKey(name = "fing3Cc#4a!!w&^8^")
        val ACCESS_TOKEN = stringPreferencesKey(name = "access_token")
        val REFRESH_TOKEN = stringPreferencesKey(name = "refresh_token")
        val EXPIRATION_TIME = stringPreferencesKey(name = "expiration_time") //access token expiration
        val ORGANIZATION = stringPreferencesKey(name = "organization")
        val USER_ROLE = stringPreferencesKey(name = "user_role")
        val BEFORE_LOGGED_IN = booleanPreferencesKey(name = "before_logged_in")
        val IS_FORCE_APP_UPDATE = booleanPreferencesKey(name = "is_force_app_update")
        val NEW_AD_HOME_STATUS = stringPreferencesKey(name = "new_ad_home_status")
        val NEW_AD_HOME_TYPE = stringPreferencesKey(name = "new_ad_home_type")
        val NEW_AD_HOME_PROPERTY = stringPreferencesKey(name = "new_ad_home_property")
        val NEW_AD_HOME_DESCRIPTION = stringPreferencesKey(name = "new_ad_home_description")
        val NEW_AD_HOME_CONDITION = stringPreferencesKey(name = "new_ad_home_condition")
        val NEW_AD_HOME_ADDRESS = stringPreferencesKey(name = "new_ad_home_adesss")
        val NEW_AD_HOME_ADD_IMAGE = stringPreferencesKey(name = "new_ad_home_add_image")
    }

}