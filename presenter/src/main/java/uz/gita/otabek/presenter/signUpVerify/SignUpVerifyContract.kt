package uz.gita.otabek.presenter.signUpVerify

import org.orbitmvi.orbit.ContainerHost

interface SignUpVerifyContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToBack()
        suspend fun moveToPassword()
    }


    interface Intent {
        data class ClickNext(
            val code: String
        ) : Intent

        data object MoveToBack : Intent

        data object ResendCode : Intent
    }
}