package model.transaction.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import model.transaction.domain.Transaction

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_income")
    val isIncome: Boolean,
    @ColumnInfo(name = "summa")
    val summa: Double
    )

fun Transaction.toEntity() = TransactionEntity(name = name, isIncome = isIncome, summa = summa)
