package screens.transactionslist

import androidx.compose.runtime.Immutable
import model.domain.Transaction
import mvi.UiAction
import mvi.UiEvent
import mvi.UiState

@Immutable
sealed class TransactionsListUiEvent : UiEvent {
    data class ShowTransactionsList(val transactions: List<Transaction>) : TransactionsListUiEvent()
}

@Immutable
data class TransactionsListState(
    val transactions: List<Transaction>
): UiState{
    companion object{
        fun initial() = TransactionsListState(
            transactions = emptyList()
        )
    }
}

@Immutable
sealed class TransactionsListAction : UiAction {

}
