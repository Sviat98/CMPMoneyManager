package model.settings.domain

sealed class Language(val isoFormat: String, val label: String) {
    data object English : Language("en", "English")
    data object Russian : Language("ru", "Русский")
}

val LANGUAGES = listOf(Language.English, Language.Russian)