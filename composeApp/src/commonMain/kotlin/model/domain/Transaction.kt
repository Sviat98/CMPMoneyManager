package model.domain

data class Transaction(
    val id: Int=0,
    val name: String,
    val isIncome: Boolean,
    val summa: Double
)
