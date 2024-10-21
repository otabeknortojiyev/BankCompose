package uz.gita.otabek.presenter.tabs.transfers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val direction: TransferContracts.Directions,
) : TransferContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: TransferContracts.Intent) = intent {
        when (intent) {
            TransferContracts.Intent.MoveToByPhoneNumber -> {
                direction.moveToByPhoneNumber()
            }

            TransferContracts.Intent.MoveToBetweenMyCards -> {
                direction.moveToBetweenMyCards()
            }

            TransferContracts.Intent.MoveToGoldenCrown -> {
                direction.moveToGoldenCrown()
            }

            TransferContracts.Intent.MoveToVISADirect -> {
                direction.moveToVISADirect()
            }
        }
    }

    override val container = container<TransferContracts.UiState, TransferContracts.SideEffect>(TransferContracts.UiState())
}