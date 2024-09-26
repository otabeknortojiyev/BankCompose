package uz.gita.otabek.presenter.addCard

import org.orbitmvi.orbit.ContainerHost
import uz.gita.otabek.common.request.CardRequest

interface AddCardContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        var isLoading: Boolean = false,
        var success: Boolean = false,
        var error: Boolean = false
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToHome()
    }

    interface Intent {
        data object MoveToHome : Intent
        data class AddCard(val addCardRequest: CardRequest.AddCard) : Intent
        data object CloseDialog : Intent
    }
}