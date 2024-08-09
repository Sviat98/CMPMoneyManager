package di

import model.db.getDatabaseBuilder
import org.koin.dsl.module

actual val databaseBuilderModule = module {
    single {
        getDatabaseBuilder(context = get())
    }
}