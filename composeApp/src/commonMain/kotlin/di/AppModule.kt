package di

import AppViewModel
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import model.datastore.TransactionDatastore
import model.datastore.createDatastore
import model.db.TransactionDatabase
import model.db.getRoomDatabase
import model.settings.repository.SettingsRepository
import model.settings.repository.SettingsRepositoryImpl
import model.transaction.repository.TransactionsRepository
import model.transaction.repository.TransactionsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::TransactionsRepositoryImpl) {
        bind<TransactionsRepository>()
    }

    singleOf(::SettingsRepositoryImpl) {
        bind<SettingsRepository>()
    }

    single {
        val builder: RoomDatabase.Builder<TransactionDatabase> = get()
        getRoomDatabase(builder = builder)
    }

    singleOf(::AppViewModel)

    single {
        val database: TransactionDatabase = get()
        database.transactionDao()
    }

    single {
        val prefs: DataStore<Preferences> = get()
        TransactionDatastore(dataStore = prefs)
    }
}
