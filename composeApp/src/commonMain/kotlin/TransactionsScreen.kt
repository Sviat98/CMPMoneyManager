import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt


@Composable
fun TransactionsScreen(
    transactions: List<Transaction>,
    onDialogOpen: (Boolean)->Unit,
    onDeleteList: ()-> Unit
) {
//    var dialogState by remember {
//        mutableStateOf<TransactionDialogState>(TransactionDialogState.Closed)
//    }

    val balance by remember {
        derivedStateOf {
            transactions.sumOf { it.summa }
        }
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
            RoundedButton(onClick = { onDeleteList() }) {
                Text(text = "AC", fontSize = 40.sp)
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transactions) { transaction ->
                TransactionItem(
                    name = transaction.name,
                    summa = transaction.summa
                )
            }
        }
    }
}

@Composable
fun TransactionItem(
    name: String = "",
    summa: Double = 1.0
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, color = MaterialTheme.colors.onPrimary, fontSize = 24.sp)
        Text(
            text= "$summa",
            fontSize = 24.sp,
            color = if (summa < 0.0) Color.Red else Color.Green
        )
    }
}

sealed class TransactionDialogState {
    object Closed : TransactionDialogState()
    object Income: TransactionDialogState()
    object Outcome: TransactionDialogState()
    //data class Opened(val isIncome: Boolean) : TransactionDialogState()
}

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

data class Transaction(
    val name: String,
    val summa: Double
)