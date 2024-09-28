package uz.gita.otabek.presenter.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.home.LastTransfersUseCase
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val directions: MonitoringContract.Directions,
    private val lastTransfersUseCase: LastTransfersUseCase
) : ViewModel(), MonitoringContract.ViewModel {
    override fun onEventDispatcher(intent: MonitoringContract.Intent) = intent {
        when (intent) {
            MonitoringContract.Intent.MoveToHome -> {
                directions.moveToHome()
            }

            MonitoringContract.Intent.DownloadLastTransfers -> {
                viewModelScope.launch {
                    val result = lastTransfersUseCase()
                    result.onSuccess {
                        if (it.isEmpty()) {
                            reduce { state.copy(hasTransfers = false) }
                        } else {
                            reduce { state.copy(lastTransfers = it) }
                        }
                    }.onFailure {

                    }
                }
            }
        }
    }

    override val container = container<MonitoringContract.UiState, MonitoringContract.SideEffect>(MonitoringContract.UiState())
}