package uz.gita.otabek.presenter.tabs.transfers

import org.orbitmvi.orbit.ContainerHost
import uz.gita.otabek.common.RecentTransfersData

interface TransferContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val resentTransfers: List<RecentTransfersData> = emptyList()
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToByPhoneNumber()
        suspend fun moveToBetweenMyCards()
        suspend fun moveToGoldenCrown()
        suspend fun moveToVISADirect()
    }

    interface Intent {
        data object MoveToByPhoneNumber : Intent
        data object MoveToBetweenMyCards : Intent
        data object MoveToGoldenCrown : Intent
        data object MoveToVISADirect : Intent
    }
}