package model.settings.repository

import kotlinx.coroutines.flow.Flow
import model.settings.domain.SettingsLocale

interface SettingsRepository {
    fun observeLocale(): Flow<SettingsLocale>
    suspend fun setLocale(locale: SettingsLocale)
}