package uz.gita.otabek.bankauthcompose.screens.language

import org.orbitmvi.orbit.ContainerHost

interface LanguageContract {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToRegister()
    }

    interface Intent {
        object ClickLanguage : Intent
        data class SetLanguage(val lang: String) : Intent
    }
}