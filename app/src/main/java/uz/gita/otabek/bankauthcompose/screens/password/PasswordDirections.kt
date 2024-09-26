package uz.gita.otabek.bankauthcompose.screens.password

import uz.gita.otabek.bankauthcompose.screens.main.MainScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.password.PasswordContract
import javax.inject.Inject

class PasswordDirections @Inject constructor(private val navigator: AppNavigator) : PasswordContract.Directions {
    override suspend fun moveToHome() {
        navigator.replace(MainScreen)
    }
}