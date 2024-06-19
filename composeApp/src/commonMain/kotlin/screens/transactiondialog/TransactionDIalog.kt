package screens.transactiondialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    viewModel: TransactionDialogViewModel,
    isIncome: Boolean = false,
) {
    val state by viewModel.state.collectAsState()


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
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                colors =
                    TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onPrimary,
                        focusedIndicatorColor = MaterialTheme.colors.onPrimary,
                        ),
            )
            TextField(
                value = state.summa.toString(),
                onValueChange = { viewModel.onSummaChange(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors =
                    TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onPrimary,
                        focusedIndicatorColor = MaterialTheme.colors.onPrimary,
                    ),
            )
            Button(onClick = {
                viewModel.addTransaction(state.name,isIncome,state.summa.toDouble())
                onDismissRequest()
            }) {
                Text("Сохранить")
            }
        }
    }
}

//@Preview
//@Composable
//fun TransactionDialogPreview() {
//    TransactionDialog()
//}
