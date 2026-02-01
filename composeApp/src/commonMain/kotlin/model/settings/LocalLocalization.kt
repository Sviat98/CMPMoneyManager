package model.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import model.settings.domain.SettingsLocale

expect object LocalLocalization {
    val current: SettingsLocale @Composable get
    @Composable
    infix fun provides(value: SettingsLocale?): ProvidedValue<*>
}