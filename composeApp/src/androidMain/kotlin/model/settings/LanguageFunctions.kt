package model.settings

import java.util.Locale

actual fun changeLanguage(lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
}

actual fun getDefaultLanguage(): String = Locale.getDefault().language