package screens.transactionslist

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.repository.TransactionsRepository
import mvi.BaseViewModel
import mvi.Reducer

class TransactionsListViewModel(
    private val transactionsRepository: TransactionsRepository,
) : BaseViewModel<TransactionsListState, TransactionsListUiEvent, TransactionsListAction>() {
    private val reducer = MainReducer(TransactionsListState.initial())

    override val state: StateFlow<TransactionsListState>
        get() = reducer.state

    private fun sendEvent(event: TransactionsListUiEvent) {
        reducer.sendEvent(event = event)
    }

    fun deleteTransactions() {
        viewModelScope.launch {
            transactionsRepository.removeAllTransactions()
        }
    }

    init {
        viewModelScope.launch {
            transactionsRepository.getTransactions().collect { transactions ->
                println(transactions)
                sendEvent(TransactionsListUiEvent.ShowTransactionsList(transactions = transactions))
            }
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
