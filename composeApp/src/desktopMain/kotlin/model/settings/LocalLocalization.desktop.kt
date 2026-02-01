package model.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import model.settings.domain.SettingsLocale
import model.settings.domain.getDefaultSettingsLocale

actual object LocalLocalization {
    private val defaultLocale = getDefaultSettingsLocale()

    private val LocalLocalization = staticCompositionLocalOf { defaultLocale }
    actual val current: SettingsLocale
        @Composable
        get() = LocalLocalization.current

    @Composable
    actual infix fun provides(value: SettingsLocale?): ProvidedValue<*> {

        val newLocale = value ?: defaultLocale

        changeLocale(newLocale)

        return LocalLocalization provides newLocale
    }
}