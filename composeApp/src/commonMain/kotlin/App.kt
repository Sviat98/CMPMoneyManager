import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.MoneyManagerTheme

@Composable
@Preview
fun App() {
    MoneyManagerTheme {
        val navController = rememberNavController()

        val transactions =
            remember {
                mutableStateListOf<Transaction>().apply {
                    add(Transaction("зарплата", 100.0))
                    add(Transaction("мороженое", -20.0))
                }
            }

        NavHost(navController = navController, startDestination = Screens.Transactions.route) {
            composable(route = Screens.Transactions.route) {
                TransactionsScreen(
                    transactions = transactions,
                    onDialogOpen = {
                        navController.navigate(
                            route = Screens.Dialog.route.replace("{isIncome}", it.toString()),
                        )
                    },
                    onDeleteList = { transactions.clear() },
                )
            }
            dialog(
                route = Screens.Dialog.route,
                arguments = listOf(navArgument("isIncome") { defaultValue = false }),
            ) { navBackStackEntry ->
                val isIncome = navBackStackEntry.arguments?.getBoolean("isIncome") ?: false
                TransactionDialog(
                    onDismissRequest = { navController.popBackStack() },
                    isIncome = isIncome,
                    onTransactionAdd = {transactions.add(it)}
                )
            }
        }
    }
}

sealed class Screens(
    val route: String,
) {
    data object Transactions : Screens("transactions")

    data object Dialog : Screens("dialog?isIncome={isIncome}")
}
