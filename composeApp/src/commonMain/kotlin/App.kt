import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.currentKoinScope
import screens.transactiondialog.TransactionDialog
import screens.transactiondialog.TransactionDialogViewModel
import screens.transactionslist.TransactionsListViewModel
import screens.transactionslist.TransactionsScreen
import theme.MoneyManagerTheme

@Composable
@Preview
fun App() {
    MoneyManagerTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screens.Transactions.route) {
            composable(route = Screens.Transactions.route) {

                val transactionsListViewModel = koinViewModel<TransactionsListViewModel>()
                TransactionsScreen(
                    viewModel = transactionsListViewModel,
                    onDialogOpen = {
                        navController.navigate(
                            route = Screens.Dialog.route.replace("{isIncome}", it.toString()),
                        )
                    },
                )
            }
            dialog(
                route = Screens.Dialog.route,
                arguments = listOf(navArgument("isIncome") { defaultValue = false }),
            ) { navBackStackEntry ->
                val isIncome = navBackStackEntry.arguments?.getBoolean("isIncome") ?: false

                val viewModel  = koinViewModel<TransactionDialogViewModel>()
                TransactionDialog(
                    onDismissRequest = { navController.popBackStack() },
                    viewModel = viewModel,
                    isIncome = isIncome,
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

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
