package model.settings.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import model.datastore.TransactionDatastore
import model.settings.domain.LOCALES
import model.settings.domain.SettingsLocale

class SettingsRepositoryImpl(
    private val datastore: TransactionDatastore
): SettingsRepository {
    override fun observeLocale(): Flow<SettingsLocale> = datastore.observeLocaleString().distinctUntilChanged().map { localeString->
        val (language,country) = localeString.split('_')

        val locale = LOCALES.find { it.isoFormat == language && it.country==country} ?: SettingsLocale.English_UK
        locale
    }

    override suspend fun setLocale(locale: SettingsLocale){
        val localeString = "${locale.isoFormat}_${locale.country}"

        datastore.setLocaleString(localeString)
    }
}