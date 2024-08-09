package model.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<TransactionDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "transaction.db")
    return Room.databaseBuilder<TransactionDatabase>(
        name = dbFile.absolutePath,
    )
}