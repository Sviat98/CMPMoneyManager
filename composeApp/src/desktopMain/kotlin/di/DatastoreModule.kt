package di

import model.datastore.DATASTORE_FILE_NAME
import model.datastore.createDatastore
import org.koin.dsl.module
import java.io.File

actual val datastoreCreatorModule  = module {
    val prefsFile = File(System.getProperty("java.io.tmpdir"), DATASTORE_FILE_NAME)

    single {
        createDatastore {
            prefsFile.absolutePath
        }
    }
}