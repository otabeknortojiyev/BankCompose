package uz.gita.otabek.bankauthcompose.screens.addCard

import uz.gita.otabek.bankauthcompose.screens.main.MainScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.addCard.AddCardContract
import javax.inject.Inject

class AddCardDirections @Inject constructor(private val navigator: AppNavigator) : AddCardContract.Directions {
    override suspend fun moveToHome() {
        navigator.replace(MainScreen)
    }
}