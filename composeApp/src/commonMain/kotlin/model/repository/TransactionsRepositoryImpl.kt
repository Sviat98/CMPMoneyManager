package model.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.domain.Transaction

class TransactionsRepositoryImpl : TransactionsRepository {
    private val _transactions =
        MutableStateFlow(
            listOf(
                Transaction(name = "зарплата", isIncome = true, summa = 100.0),
                Transaction(name = "мороженое", isIncome = false, summa = 20.0),
            ),
        )

    override val transactions: StateFlow<List<Transaction>>
        get() = _transactions.asStateFlow()

//    override fun getTransactions(): StateFlow<List<Transaction>> = _transactions.asStateFlow()

    override suspend fun addTransaction(transaction: Transaction) {

        _transactions.value += transaction
    }

    override fun removeAllTransactions() {
        _transactions.value = emptyList()
    }
}
