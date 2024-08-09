package di

import androidx.room.RoomDatabase
import model.db.TransactionDao
import model.db.TransactionDatabase
import model.db.getRoomDatabase
import model.repository.TransactionsRepository
import model.repository.TransactionsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TransactionsRepositoryImpl){
        bind<TransactionsRepository>()
    }

    single {
        val builder: RoomDatabase.Builder<TransactionDatabase> = get()
        getRoomDatabase(builder = builder)
    }

    single {
        val database: TransactionDatabase = get()
        database.transactionDao()
    }
}
