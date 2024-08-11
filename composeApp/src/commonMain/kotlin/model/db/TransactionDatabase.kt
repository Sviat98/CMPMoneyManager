package model.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [TransactionEntity::class],
    version = 1
)
@ConstructedBy(TransactionDatabaseCreator::class) // NEW
abstract class TransactionDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<TransactionDatabase>
): TransactionDatabase {
    return builder
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}