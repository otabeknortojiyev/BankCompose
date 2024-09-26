package uz.gita.otabek.presenter.monitoring

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                lastTransfersUseCase()
                    .onEach {
                        it.onSuccess {
                            if (it.isEmpty()) {
                                reduce { state.copy(hasTransfers = false) }
                            } else {
                                reduce { state.copy(lastTransfers = it) }
                            }
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
            }
        }
    }

    override val container = container<MonitoringContract.UiState, MonitoringContract.SideEffect>(MonitoringContract.UiState())
}