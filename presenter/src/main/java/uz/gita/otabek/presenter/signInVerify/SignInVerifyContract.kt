package uz.gita.otabek.presenter.signInVerify

import org.orbitmvi.orbit.ContainerHost

interface SignInVerifyContract {
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
        suspend fun moveToPassword()
        suspend fun moveToSignIn()
    }

    interface Intent {
        data class MoveToPassword(val code: String) : Intent
        data object MoveToSignIn : Intent
        data object ResendCode : Intent
    }
}