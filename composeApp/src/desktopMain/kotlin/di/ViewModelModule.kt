package di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import screens.transactiondialog.TransactionDialogViewModel
import screens.transactionslist.TransactionsListViewModel

actual val viewModelModule = module {
    singleOf(::TransactionsListViewModel)
    factoryOf(::TransactionDialogViewModel)
}