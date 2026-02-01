import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import model.settings.LocalLocalization
import navigation.TransactionDialogDestination
import navigation.TransactionsListDestination
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import screens.transactiondialog.TransactionDialog
import screens.transactiondialog.TransactionDialogViewModel
import screens.transactionslist.TransactionsListViewModel
import screens.transactionslist.TransactionsScreen
import theme.MoneyManagerTheme


@Composable
@Preview
fun App() {
    val appViewModel = koinViewModel<AppViewModel>()

    val appState = appViewModel.state.collectAsStateWithLifecycle()

    val locale = appState.value.locale

    println(locale)

    CompositionLocalProvider(
        LocalLocalization provides locale
    ) {
        MoneyManagerTheme {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = TransactionsListDestination) {
                composable<TransactionsListDestination> {
                    val transactionsListViewModel = koinViewModel<TransactionsListViewModel>()
                    TransactionsScreen(
                        viewModel = transactionsListViewModel,
                        onDialogOpen = { isIncome ->
                            navController.navigate(
                                route = TransactionDialogDestination(isIncome = isIncome),
                            )
                        },
                    )
                }
                dialog<TransactionDialogDestination> { navBackStackEntry ->
                    val transactionDialogDestination: TransactionDialogDestination =
                        navBackStackEntry.toRoute()

                    val isIncome = transactionDialogDestination.isIncome

                    val viewModel = koinViewModel<TransactionDialogViewModel>()
                    TransactionDialog(
                        onDismissRequest = { navController.navigateUp() },
                        viewModel = viewModel,
                        isIncome = isIncome,
                    )
                }
            }
        }
    }
}
