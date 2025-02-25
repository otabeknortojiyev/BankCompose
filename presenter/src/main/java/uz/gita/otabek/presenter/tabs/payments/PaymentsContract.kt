package uz.gita.otabek.presenter.tabs.payments

import org.orbitmvi.orbit.ContainerHost

interface PaymentsContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect

    interface Direction {

    }

    interface Intent {

    }
}