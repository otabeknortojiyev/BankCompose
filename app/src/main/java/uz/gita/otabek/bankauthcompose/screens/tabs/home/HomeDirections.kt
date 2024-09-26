package uz.gita.otabek.bankauthcompose.screens.tabs.home

import uz.gita.otabek.bankauthcompose.screens.addCard.AddCardScreen
import uz.gita.otabek.bankauthcompose.screens.monitoring.MonitoringScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.tabs.home.HomeContracts
import javax.inject.Inject

class HomeDirections @Inject constructor(private val navigator: AppNavigator) : HomeContracts.Directions {
    override suspend fun moveToMonitoring() {
        navigator.push(MonitoringScreen)
    }

    override suspend fun moveToProfile() {

    }

    override suspend fun moveToGoldenCrown() {

    }

    override suspend fun moveToAddCard() {
        navigator.push(AddCardScreen)
    }

    override suspend fun moveToBranches() {

    }

    override suspend fun moveToATMs() {

    }
}