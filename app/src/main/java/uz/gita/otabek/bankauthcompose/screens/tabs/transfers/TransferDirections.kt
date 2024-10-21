package uz.gita.otabek.bankauthcompose.screens.tabs.transfers

import uz.gita.otabek.bankauthcompose.screens.transfersBetweenMyCacrds.TransfersBetweenMyCardsScreen
import uz.gita.otabek.bankauthcompose.screens.transfersByPhoneNumber.TransfersByPhoneNumberScreen
import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.tabs.transfers.TransferContracts
import javax.inject.Inject

class TransferDirections @Inject constructor(private val navigator: AppNavigator) : TransferContracts.Directions {
    override suspend fun moveToByPhoneNumber() {
        navigator.push(TransfersByPhoneNumberScreen)
    }

    override suspend fun moveToBetweenMyCards() {
        navigator.push(TransfersBetweenMyCardsScreen)
    }

    override suspend fun moveToGoldenCrown() {
//        navigator.push(GoldenCrownScreen)
    }

    override suspend fun moveToVISADirect() {
//        navigator.push(VISADirectScreen)
    }
}