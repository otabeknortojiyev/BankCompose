package uz.gita.otabek.bankauthcompose.screens.language

import uz.gita.otabek.bankauthcompose.screens.signUp.SignUpScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import javax.inject.Inject

class LanguageDirections @Inject constructor(private val navigator: AppNavigator) : LanguageContract.Direction {
    override suspend fun moveToRegister() {
        navigator.push(SignUpScreen)
    }
}