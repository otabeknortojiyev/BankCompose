package uz.gita.otabek.presenter.transfersByPhoneNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.domain.useCase.card.GetCardsUseCase
import uz.gita.otabek.domain.useCase.transfer.GetCardOwnerByPanUseCase
import uz.gita.otabek.domain.useCase.transfer.GetFeeUseCase
import uz.gita.otabek.domain.useCase.transfer.TransferUseCase
import javax.inject.Inject

@HiltViewModel
class TransfersByPhoneNumberViewModel @Inject constructor(
    private val directions: TransfersByPhoneNumberContract.Directions,
    private val getCardOwnerByPan: GetCardOwnerByPanUseCase,
    private val getFee: GetFeeUseCase,
    private val transfer: TransferUseCase,
    private val getCards: GetCardsUseCase,
) : ViewModel(), TransfersByPhoneNumberContract.ViewModel {
    override fun onEventDispatcher(intent: TransfersByPhoneNumberContract.Intent) = intent {
        when (intent) {
            is TransfersByPhoneNumberContract.Intent.GetCardOwnerByPan -> {
                getCardOwnerByPan.invoke(TransferRequest.GetCardOwnerByPan(intent.receiverPan)).onStart {
                    reduce { state.copy(isLoading = true) }
                }.onEach { result ->
                    result.onSuccess {
                        reduce { state.copy(receiverName = it.pan) }
                    }.onFailure {

                    }
                }.onCompletion { reduce { state.copy(isLoading = false) } }.launchIn(viewModelScope)
            }

            is TransfersByPhoneNumberContract.Intent.GetFee -> {
                getFee.invoke(TransferRequest.GetFee(intent.senderId, intent.receiver, intent.amount)).onStart {
                    reduce { state.copy(isLoading = true) }
                }.onEach { result ->
                    result.onSuccess {
                        reduce { state.copy(fee = it.fee, amountWithFee = it.amount) }
                    }.onFailure {

                    }
                }.onCompletion { reduce { state.copy(isLoading = false) } }.launchIn(viewModelScope)
            }

            is TransfersByPhoneNumberContract.Intent.MakeTransfer -> {
                transfer.invoke(TransferRequest.Transfer(intent.type, intent.senderId, intent.receiverPan, intent.amount)).onStart {
                    reduce { state.copy(isLoading = true) }
                }.onEach { result ->
                    result.onSuccess {
                        directions.moveToTransferVerify()
                    }.onFailure {

                    }
                }.onCompletion { reduce { state.copy(isLoading = false) } }.launchIn(viewModelScope)
            }

            TransfersByPhoneNumberContract.Intent.GetCards -> {
                getCards.invoke().onEach { result ->
                    result.onSuccess {
                        reduce { state.copy(cards = it, balance = it[0].amount) }
                    }.onFailure {

                    }
                }.launchIn(viewModelScope)
            }

            TransfersByPhoneNumberContract.Intent.MoveToBack -> {
                directions.moveToBack()
            }
        }
    }

    override val container =
        container<TransfersByPhoneNumberContract.UiState, TransfersByPhoneNumberContract.SideEffect>(TransfersByPhoneNumberContract.UiState())
}