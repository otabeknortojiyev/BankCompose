package uz.gita.otabek.bankauthcompose.screens.signInVerify

import uz.gita.otabek.bankauthcompose.screens.password.PasswordScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.signInVerify.SignInVerifyContract
import javax.inject.Inject

class SignInVerifyDirections @Inject constructor(private val navigator: AppNavigator) : SignInVerifyContract.Directions {
    override suspend fun moveToPassword() {
        navigator.replaceAll(PasswordScreen)
    }

    override suspend fun moveToSignIn() {
        navigator.back()
    }
}