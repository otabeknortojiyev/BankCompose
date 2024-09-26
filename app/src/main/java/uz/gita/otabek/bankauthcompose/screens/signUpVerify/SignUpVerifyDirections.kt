package uz.gita.otabek.bankauthcompose.screens.signUpVerify

import uz.gita.otabek.bankauthcompose.screens.password.PasswordScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.signUpVerify.SignUpVerifyContract
import javax.inject.Inject

class SignUpVerifyDirections @Inject constructor(private val navigator: AppNavigator) : SignUpVerifyContract.Directions {
    override suspend fun moveToPassword() {
        navigator.replaceAll(PasswordScreen)
    }

    override suspend fun moveToBack() {
        navigator.back()
    }
}