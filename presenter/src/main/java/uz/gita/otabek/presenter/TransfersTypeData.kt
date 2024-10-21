package uz.gita.otabek.presenter

import uz.gita.otabek.presenter.tabs.transfers.TransferContracts

data class TransfersTypeData(
    val image: Int,
    val title: Int,
    val intent: TransferContracts.Intent
)
