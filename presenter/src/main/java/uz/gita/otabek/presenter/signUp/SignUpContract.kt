package uz.gita.otabek.presenter.signUp

import org.orbitmvi.orbit.ContainerHost

interface SignUpContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToCode(phone: String)
        suspend fun moveToBack()
        suspend fun moveToSignIn()
    }


    interface Intent {
        data class ClickNext(
            val number: String, val name: String, val surname: String, val bornDate: String, val password: String, val gender: String
        ) : Intent

        object MoveToBack : Intent
        object MoveToSignIn : Intent
    }
}