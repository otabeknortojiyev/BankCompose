package uz.gita.otabek.bankauthcompose.screens.splash

import uz.gita.otabek.bankauthcompose.screens.language.LanguageScreen
import uz.gita.otabek.bankauthcompose.screens.password.PasswordScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import javax.inject.Inject

class SplashDirections @Inject constructor(private val appNavigator: AppNavigator) : SplashContract.Direction {
    override suspend fun moveToLanguage() {
        appNavigator.replace(LanguageScreen)
    }

    override suspend fun moveToPIN() {
        appNavigator.replace(PasswordScreen)
    }
}