package uz.gita.otabek.bankauthcompose.screens.splash

import uz.gita.otabek.bankauthcompose.screens.language.LanguageScreen
import uz.gita.otabek.bankauthcompose.screens.passwordVerify.PasswordVerifyScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.splash.SplashContract
import javax.inject.Inject

class SplashDirections @Inject constructor(private val appNavigator: AppNavigator) : SplashContract.Direction {
    override suspend fun moveToLanguage() {
        appNavigator.replace(LanguageScreen)
    }

    override suspend fun moveToPIN() {
        appNavigator.replace(PasswordVerifyScreen)
    }
}