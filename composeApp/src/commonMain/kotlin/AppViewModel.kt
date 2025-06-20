import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import model.settings.domain.SettingsLocale
import model.settings.repository.SettingsRepository
import mvi.UiState

class AppViewModel(
    private val settingsRepository: SettingsRepository
): ViewModel() {

    private val _state = MutableStateFlow(AppState.initial())

    val state: StateFlow<AppState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.observeLocale().distinctUntilChanged().collect{locale->
                _state.value = _state.value.copy(locale = locale)
                println("AppViewModel locale = $locale")
            }
        }
    }
}

@Immutable
data class AppState(
    val locale: SettingsLocale
): UiState {
    companion object{
        fun initial() = AppState(
            locale = SettingsLocale.English_UK
        )
    }
}