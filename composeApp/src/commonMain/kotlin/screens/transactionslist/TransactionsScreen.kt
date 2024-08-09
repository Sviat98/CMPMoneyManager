package screens.transactionslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@Composable
fun TransactionsScreen(
    viewModel: TransactionsListViewModel,
    onDialogOpen: (Boolean) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    val transactions = state.transactions

    val balance by derivedStateOf {
        transactions.sumOf { transaction -> if (transaction.isIncome) transaction.summa else (-1) * transaction.summa }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Актуальный баланс", color = MaterialTheme.colors.onPrimary)
        Text(
            text = "$balance $",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 60.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedButton(onClick = {
                onDialogOpen(true)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    modifier = Modifier.size(50.dp),
                    contentDescription = null
                )
            }
            RoundedButton(onClick = {
                onDialogOpen(false)
            }) {
                Text(text = "-", fontSize = 60.sp)
            }
            RoundedButton(onClick = { viewModel.deleteTransactions() }) {
                Text(text = "AC", fontSize = 40.sp)
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth().background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(12.dp)
            )
        ) {
            if (transactions.isEmpty()) {
                Text(
                    "Здесь пока нет транзакций",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.onPrimary
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(transactions) { transaction ->
                        TransactionItem(
                            name = transaction.name,
                            isIncome = transaction.isIncome,
                            summa = transaction.summa
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(
    name: String = "",
    isIncome: Boolean,
    summa: Double = 1.0
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, color = MaterialTheme.colors.onPrimary, fontSize = 24.sp)
        Text(
            text = if (isIncome) "+$summa" else "-$summa",
            fontSize = 24.sp,
            color = if (isIncome) Color.Green else Color.Red
        )
    }
}

//sealed class TransactionDialogState {
//    object Closed : TransactionDialogState()
//    object Income: TransactionDialogState()
//    object Outcome: TransactionDialogState()
//    //data class Opened(val isIncome: Boolean) : TransactionDialogState()
//}

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonContent: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(90.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        buttonContent()
    }
}

//fun Double.formatToDecimalValue() = DecimalFormat("0.00").format(this)

fun Double.formatToDecimalValue(decimals: Int): Double {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = (this * dotAt).roundToInt()
    return (roundedValue / dotAt) + (roundedValue % dotAt).toDouble() / dotAt
}

