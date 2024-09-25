package uz.gita.otabek.bankauthcompose.screens.signUp

import uz.gita.otabek.bankauthcompose.screens.signUpVerify.SignUpVerifyScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import javax.inject.Inject

class SignUpDirections @Inject constructor(private val navigator: AppNavigator) : SignUpContract.Direction {
    override suspend fun moveToCode(phone: String) {
        navigator.push(SignUpVerifyScreen(phone = phone))
    }

    override suspend fun moveToBack() {
        navigator.back()
    }
}