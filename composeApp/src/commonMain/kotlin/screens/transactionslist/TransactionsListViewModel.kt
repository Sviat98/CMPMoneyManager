package screens.transactionslist

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.settings.changeLocale
import model.settings.domain.SettingsLocale
import model.settings.repository.SettingsRepository
import model.transaction.repository.TransactionsRepository
import mvi.BaseViewModel
import mvi.Reducer

class TransactionsListViewModel(
    private val transactionsRepository: TransactionsRepository,
    private val settingsRepository: SettingsRepository
) : BaseViewModel<TransactionsListState, TransactionsListUiEvent, TransactionsListAction>() {
    private val reducer = MainReducer(TransactionsListState.initial())

    override val state: StateFlow<TransactionsListState>
        get() = reducer.state

    private fun sendEvent(event: TransactionsListUiEvent) {
        reducer.sendEvent(event = event)
    }

    init {
        viewModelScope.launch {
            transactionsRepository.getTransactions().collect { transactions ->
                println(transactions)
                sendEvent(TransactionsListUiEvent.ShowTransactionsList(transactions = transactions))
            }
        }

        viewModelScope.launch {
            settingsRepository.observeLocale().collect { locale ->
                changeLocale(locale)
                println("TransactionsListViewModel locale = $locale")
                //sendEvent(TransactionsListUiEvent.ChangeLanguage(language = language))
            }
        }
    }

    fun deleteTransactions() {
        viewModelScope.launch {
            transactionsRepository.removeAllTransactions()
        }
    }

    fun setLocale(settingsLocale: SettingsLocale) {
        viewModelScope.launch {
            settingsRepository.setLocale(settingsLocale)
        }
    }

    private class MainReducer(
        initial: TransactionsListState,
    ) : Reducer<TransactionsListState, TransactionsListUiEvent>(initial) {
        override fun reduce(
            oldState: TransactionsListState,
            event: TransactionsListUiEvent,
        ) {
            when (event) {
                is TransactionsListUiEvent.ShowTransactionsList -> {
                    setState(oldState.copy(transactions = event.transactions))
                }

                else -> {}
            }
        }
    }
}
