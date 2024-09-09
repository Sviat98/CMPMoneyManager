package navigation

import kotlinx.serialization.Serializable

@Serializable
data object TransactionsListDestination

@Serializable
data class TransactionDialogDestination(val isIncome: Boolean)