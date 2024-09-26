package uz.gita.otabek.presenter.monitoring

import org.orbitmvi.orbit.ContainerHost
import uz.gita.otabek.common.response.HomeResponse

interface MonitoringContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        var error: Boolean = false,
        var hasTransfers: Boolean = false,
        var lastTransfers: ArrayList<HomeResponse.TransferItem> = arrayListOf()
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToHome()
    }

    interface Intent {
        data object MoveToHome : Intent
        data object DownloadLastTransfers : Intent
    }
}