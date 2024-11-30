package model.settings.repository

import kotlinx.coroutines.flow.Flow
import model.settings.domain.Language

interface SettingsRepository {
    fun observeLanguage(): Flow<Language>
    suspend fun setLanguage(lang: String)
}