package di

import model.datastore.createDatastore
import org.koin.dsl.module

actual val datastoreCreatorModule = module {
    single {
        createDatastore(context = get())
    }
}