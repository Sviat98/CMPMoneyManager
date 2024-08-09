import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.appModule
import di.databaseBuilderModule
import di.viewModelModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModule, viewModelModule, databaseBuilderModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "CMPMoneyManager",
    ) {
        App()
    }
}