package uz.gita.otabek.presenter.transfersBetweenMyCards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.presenter.utils.Lang
import javax.inject.Inject

@HiltViewModel
class TransfersBetweenMyCardsViewModel @Inject constructor(
    private val direction: TransfersBetweenMyCardsContract.Direction
) : ViewModel(), TransfersBetweenMyCardsContract.ViewModel {

    override fun onEventDispatcher(intent: TransfersBetweenMyCardsContract.Intent) = intent {
        when (intent) {

        }
    }

    override val container =
        container<TransfersBetweenMyCardsContract.UiState, TransfersBetweenMyCardsContract.SideEffect>(TransfersBetweenMyCardsContract.UiState())
}