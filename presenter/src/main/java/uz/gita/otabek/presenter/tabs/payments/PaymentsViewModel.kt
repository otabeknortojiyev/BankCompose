package uz.gita.otabek.presenter.tabs.payments

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class PaymentsViewModel @Inject constructor(private val direction: PaymentsContract.Direction) : PaymentsContract.ViewModel, ViewModel() {
    override fun onEventDispatcher(intent: PaymentsContract.Intent) = intent {
        when (intent) {

        }
    }

    override val container = container<PaymentsContract.UiState, PaymentsContract.SideEffect>(PaymentsContract.UiState())
}