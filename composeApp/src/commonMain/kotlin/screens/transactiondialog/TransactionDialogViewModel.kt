package screens.transactiondialog

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.transaction.domain.Transaction
import model.transaction.repository.TransactionsRepository
import mvi.BaseViewModel
import mvi.Reducer

class TransactionDialogViewModel(
    private val transactionsRepository: TransactionsRepository
): BaseViewModel<TransactionDialogState,TransactionDialogUiEvent,TransactionDialogAction>() {

    private val reducer = MainReducer(TransactionDialogState.initial())

    override val state: StateFlow<TransactionDialogState>
        get() = reducer.state

    private fun sendEvent(event: TransactionDialogUiEvent){
        reducer.sendEvent(event = event)
    }

    fun onNameChange(name: String) {
        sendEvent(TransactionDialogUiEvent.OnTransactionNameChanged(name))
    }

    fun onSummaChange(summa: String) {
        sendEvent(TransactionDialogUiEvent.OnTransactionSumChanged(summa))
    }

    fun addTransaction(name: String, isIncome: Boolean, summa: Double){
        viewModelScope.launch {
            val transaction = Transaction(name = name, isIncome = isIncome, summa = summa)
            transactionsRepository.addTransaction(transaction)
        }
    }


    private class MainReducer(initial: TransactionDialogState) :
        Reducer<TransactionDialogState, TransactionDialogUiEvent>(initial) {
        override fun reduce(oldState: TransactionDialogState, event: TransactionDialogUiEvent) {
            when (event) {
                is TransactionDialogUiEvent.OnTransactionNameChanged->{
                    setState(oldState.copy(name = event.name))
                }
                is TransactionDialogUiEvent.OnTransactionSumChanged->{
                    setState(oldState.copy(summa = event.sum))
                }
                else -> {}
            }
        }
    }
}