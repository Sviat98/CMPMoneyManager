package screens.transactiondialog

import androidx.compose.runtime.Immutable
import mvi.UiAction
import mvi.UiEvent
import mvi.UiState

@Immutable
sealed class TransactionDialogUiEvent : UiEvent {
    data class OnTransactionNameChanged(val name: String) : TransactionDialogUiEvent()
    data class OnTransactionSumChanged(val sum: String) : TransactionDialogUiEvent()

}

@Immutable
data class TransactionDialogState(
    val name: String,
    val summa: String
): UiState{
    companion object{
        fun initial() = TransactionDialogState(
            name = "",
            summa = ""
        )
    }
}

@Immutable
sealed class TransactionDialogAction : UiAction{

}