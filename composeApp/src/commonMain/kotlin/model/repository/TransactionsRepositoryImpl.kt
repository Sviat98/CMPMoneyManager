package model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import model.db.TransactionDao
import model.db.toEntity
import model.domain.Transaction
import model.domain.toDomain

class TransactionsRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionsRepository {

    override suspend fun getTransactions() = withContext(Dispatchers.IO){
        transactionDao.getTransactions().map { list->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addTransaction(transaction: Transaction) = withContext(Dispatchers.IO){
        transactionDao.insertTransaction(transaction.toEntity())
    }

    override suspend fun removeAllTransactions() = withContext(Dispatchers.IO) {
        transactionDao.deleteAll()
    }
}
