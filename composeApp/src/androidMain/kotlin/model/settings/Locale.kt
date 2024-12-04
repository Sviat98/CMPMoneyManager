package model.settings

import model.settings.domain.SettingsLocale
import java.util.Locale as JavaLocale

actual object LocaleBuilder{
    actual fun getDefault(): Locale {
        val locale = JavaLocale.getDefault()

        return Locale(locale.language,locale.country)
    }
}

actual fun changeLocale(settingsLocale: SettingsLocale) {
    val locale = JavaLocale(settingsLocale.isoFormat,settingsLocale.country)
    JavaLocale.setDefault(locale)
}
