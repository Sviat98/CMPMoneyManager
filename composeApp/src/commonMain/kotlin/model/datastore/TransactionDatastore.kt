package model.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import model.settings.Locale
import model.settings.LocaleBuilder
import model.settings.domain.LOCALES
import model.settings.domain.SettingsLocale

class TransactionDatastore(
    private val dataStore: DataStore<Preferences>
) {
    private val localeKey = stringPreferencesKey("locale")

    private val locales = LOCALES.map { Locale(it.isoFormat,it.country) }


    fun observeLocaleString() = dataStore.data.map {
        val defaultLocale = LocaleBuilder.getDefault()

        val defaultLocaleString = "${defaultLocale.language}_${defaultLocale.country}"

        println("defaultLocale = $defaultLocale")
        println("defaultLocaleString = $defaultLocaleString")
        val englishUKLocaleString = "${SettingsLocale.English_UK.isoFormat}_${SettingsLocale.English_UK.country}"
        it[localeKey] ?: if (defaultLocale in locales) defaultLocaleString else englishUKLocaleString
    }

    suspend fun setLocaleString(localeString: String) {
        dataStore.edit {
            it[localeKey] = localeString
        }
    }
}