package uz.gita.otabek.presenter.signIn

import org.orbitmvi.orbit.ContainerHost

interface SignInContract {
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
        suspend fun moveToVerify(phone: String)
        suspend fun moveToRegister()
    }

    interface Intent {
        data class MoveToVerify(val phone: String, val password: String) : Intent
        data object MoveToRegister : Intent
    }
}