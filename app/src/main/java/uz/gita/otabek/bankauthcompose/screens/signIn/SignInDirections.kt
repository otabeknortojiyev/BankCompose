package uz.gita.otabek.bankauthcompose.screens.signIn

import uz.gita.otabek.bankauthcompose.screens.signInVerify.SignInVerifyScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.signIn.SignInContract
import javax.inject.Inject

class SignInDirections @Inject constructor(private val navigator: AppNavigator) : SignInContract.Directions {
    override suspend fun moveToVerify(phone: String) {
        navigator.push(SignInVerifyScreen(phone))
    }

    override suspend fun moveToRegister() {
        navigator.back()
    }
}