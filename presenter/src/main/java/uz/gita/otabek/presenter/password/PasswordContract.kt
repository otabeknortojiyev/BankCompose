package uz.gita.otabek.presenter.password

import org.orbitmvi.orbit.ContainerHost

interface PasswordContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        var error: Boolean = false
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToHome()
    }

    interface Intent {
        data class MoveToHome(val pin: String) : Intent
    }
}