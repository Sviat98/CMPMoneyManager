package model.transaction.repository

import kotlinx.coroutines.flow.Flow
import model.transaction.domain.Transaction

interface TransactionsRepository {
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun removeAllTransactions()
}