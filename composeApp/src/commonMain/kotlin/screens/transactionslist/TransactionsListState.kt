package screens.transactionslist

import androidx.compose.runtime.Immutable
import model.settings.domain.Language
import model.transaction.domain.Transaction
import mvi.UiAction
import mvi.UiEvent
import mvi.UiState

@Immutable
sealed class TransactionsListUiEvent : UiEvent {
    data class ShowTransactionsList(val transactions: List<Transaction>) : TransactionsListUiEvent()
    data class ChangeLanguage(val language: Language) : TransactionsListUiEvent()

}

@Immutable
data class TransactionsListState(
    val transactions: List<Transaction>,
    val currentLanguage: Language
): UiState{
    companion object{
        fun initial() = TransactionsListState(
            transactions = emptyList(),
            currentLanguage = Language.English
        )
    }
}

@Immutable
sealed class TransactionsListAction : UiAction {

}
