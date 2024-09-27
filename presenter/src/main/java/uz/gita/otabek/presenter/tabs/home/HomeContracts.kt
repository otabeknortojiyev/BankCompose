package uz.gita.otabek.presenter.tabs.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.otabek.common.response.CardResponse

interface HomeContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val balance: Int = 0,
        val name: String = "",
        val cards: List<CardResponse.CardItem> = arrayListOf(),
        val totalBalance: Int = 0
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToMonitoring()
        suspend fun moveToProfile()
        suspend fun moveToGoldenCrown()
        suspend fun moveToAddCard()
        suspend fun moveToBranches()
        suspend fun moveToATMs()
    }

    interface Intent {
        data object GetInitData : Intent
        data object MoveToMonitoring : Intent
        data object MoveToAddCard : Intent
    }
}