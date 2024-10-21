package uz.gita.otabek.bankauthcompose.screens.transfersByPhoneNumber

import uz.gita.otabek.bankauthcompose.ui.navigation.AppNavigator
import uz.gita.otabek.presenter.transfersByPhoneNumber.TransfersByPhoneNumberContract
import javax.inject.Inject

class TransfersByPhoneNumberDirections @Inject constructor(private val navigator: AppNavigator) : TransfersByPhoneNumberContract.Directions {
    override suspend fun moveToTransferVerify() {
//        navigator.push(TransferVerify)
    }

    override suspend fun moveToBack() {
        navigator.back()
    }
}