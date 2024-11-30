package model.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import model.settings.domain.LANGUAGES
import model.settings.domain.Language
import model.settings.getDefaultLanguage

class TransactionDatastore(
    private val dataStore: DataStore<Preferences>
) {
    private val languageKey = stringPreferencesKey("language")

    private val languageCodes = LANGUAGES.map { it.isoFormat }

    private val defaultLanguage = getDefaultLanguage()

    fun observeLanguage() = dataStore.data.map {
        it[languageKey]
            ?: if (defaultLanguage in languageCodes) defaultLanguage else Language.English.isoFormat
    }

    suspend fun setLanguage(lang: String) {
        dataStore.edit {
            it[languageKey] = lang
        }
    }
}