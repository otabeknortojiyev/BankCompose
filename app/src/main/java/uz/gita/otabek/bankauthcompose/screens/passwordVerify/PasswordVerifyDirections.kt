package uz.gita.otabek.bankauthcompose.screens.passwordVerify

import uz.gita.otabek.bankauthcompose.screens.main.MainScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.passwordVerify.PasswordVerifyContract
import javax.inject.Inject

class PasswordVerifyDirections @Inject constructor(private val navigator: AppNavigator) : PasswordVerifyContract.Directions {
    override suspend fun moveToHome() {
        navigator.replace(MainScreen)
    }
}