package model.settings

import model.settings.domain.SettingsLocale

data class Locale (
    val language: String,
    val country: String
)

expect object LocaleBuilder{
    fun getDefault(): Locale
}


expect fun changeLocale(settingsLocale: SettingsLocale)