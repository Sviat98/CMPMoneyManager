import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionDialog(
    onDismissRequest: () -> Unit = {},
    isIncome: Boolean = false,
    onTransactionAdd: (Transaction) -> Unit = {},
) {
    var name by remember {
        mutableStateOf("")
    }

    var summa by remember {
        mutableStateOf(0.0.toString())
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = MaterialTheme.colors.background),
        ) {
            Text(
                text = if (isIncome) "Добавьте доход" else "Добавьте расход",
                color = MaterialTheme.colors.onPrimary,
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                colors =
                    TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onPrimary,
                        focusedIndicatorColor = MaterialTheme.colors.onPrimary,
                        ),
            )
            TextField(
                value = summa,
                onValueChange = { summa = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors =
                    TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onPrimary,
                        focusedIndicatorColor = MaterialTheme.colors.onPrimary,
                    ),
            )
            Button(onClick = {
                val transactionSum = if (!isIncome) (-1 * summa.toDouble()) else summa.toDouble()
                onTransactionAdd(Transaction(name, transactionSum))
                onDismissRequest()
            }) {
                Text("Сохранить")
            }
        }
    }
}

@Preview
@Composable
fun TransactionDialogPreview() {
    TransactionDialog()
}
