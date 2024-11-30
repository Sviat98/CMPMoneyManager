package model.settings.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import model.datastore.TransactionDatastore
import model.settings.domain.LANGUAGES
import model.settings.domain.Language

class SettingsRepositoryImpl(
    private val datastore: TransactionDatastore
): SettingsRepository {
    override fun observeLanguage(): Flow<Language> = datastore.observeLanguage().distinctUntilChanged().map { lang->
        val language = LANGUAGES.find { it.isoFormat == lang} ?: Language.English
        language
    }

    override suspend fun setLanguage(lang: String){
        datastore.setLanguage(lang)
    }
}