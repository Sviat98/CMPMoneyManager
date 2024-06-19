package model.repository

import kotlinx.coroutines.flow.StateFlow
import model.domain.Transaction

interface TransactionsRepository {
    fun getTransactions(): StateFlow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
    fun removeAllTransactions()
}