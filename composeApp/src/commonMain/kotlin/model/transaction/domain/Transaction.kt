package model.transaction.domain

import model.transaction.entity.TransactionEntity

data class Transaction(
    val id: Int = 0,
    val name: String,
    val isIncome: Boolean,
    val summa: Double
)

fun TransactionEntity.toDomain() =
    Transaction(id = id, name = name, isIncome = isIncome, summa = summa)
