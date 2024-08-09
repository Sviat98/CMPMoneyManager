package model.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<TransactionDatabase> {
    val dbFile = context.getDatabasePath("transaction.db")
    return Room.databaseBuilder<TransactionDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
}