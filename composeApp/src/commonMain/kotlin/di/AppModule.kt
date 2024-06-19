package di

import model.repository.TransactionsRepository
import model.repository.TransactionsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::TransactionsRepositoryImpl){
        bind<TransactionsRepository>()
    }
}