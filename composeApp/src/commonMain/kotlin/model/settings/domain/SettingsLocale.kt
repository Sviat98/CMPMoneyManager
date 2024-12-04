package model.settings.domain

sealed class SettingsLocale(val isoFormat: String,val country: String, val label: String) {

    data object English_US : SettingsLocale("en","US", "English (US)")
    data object English_UK : SettingsLocale("en","UK", "English (UK)")
    data object Russian_RU : SettingsLocale("ru","RU","Русский (Россия)")
    data object German_DE : SettingsLocale("de","DE", "Deutsch (Deutschland)")
}

val LOCALES = listOf(SettingsLocale.English_UK,SettingsLocale.English_US, SettingsLocale.Russian_RU, SettingsLocale.German_DE)