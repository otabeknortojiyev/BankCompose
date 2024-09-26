package uz.gita.otabek.bankauthcompose.screens.monitoring

import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.monitoring.MonitoringContract
import javax.inject.Inject

class MonitoringDirections @Inject constructor(private val navigator: AppNavigator) : MonitoringContract.Directions {
    override suspend fun moveToHome() {
        navigator.back()
    }
}