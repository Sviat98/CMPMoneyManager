package model.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import model.db.TransactionEntity
import model.domain.Transaction

interface TransactionsRepository {
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun removeAllTransactions()
}