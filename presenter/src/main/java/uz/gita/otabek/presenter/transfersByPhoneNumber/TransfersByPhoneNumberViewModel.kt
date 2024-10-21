package uz.gita.otabek.presenter.transfersByPhoneNumber

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private val getCards: GetCardsUseCase
) : ViewModel(), TransfersByPhoneNumberContract.ViewModel {
    override fun onEventDispatcher(intent: TransfersByPhoneNumberContract.Intent) = intent {
        when (intent) {
            is TransfersByPhoneNumberContract.Intent.GetCardOwnerByPan -> {
                reduce { state.copy(isLoading = true) }
                viewModelScope.launch {
                    getCardOwnerByPan(TransferRequest.GetCardOwnerByPan(intent.receiverPan))
                        .onSuccess {
                            reduce { state.copy(receiverName = it.pan) }
                        }.onFailure {

                        }
                    reduce { state.copy(isLoading = false) }
                }
            }

            is TransfersByPhoneNumberContract.Intent.GetFee -> {
                reduce { state.copy(isLoading = true) }
                viewModelScope.launch {
                    getFee(TransferRequest.GetFee(intent.senderId, intent.receiver, intent.amount))
                        .onSuccess {
                            reduce { state.copy(fee = it.fee, amountWithFee = it.amount) }
                        }.onFailure {

                        }
                    reduce { state.copy(isLoading = false) }
                }
            }

            is TransfersByPhoneNumberContract.Intent.MakeTransfer -> {
                reduce { state.copy(isLoading = true) }
                viewModelScope.launch {
                    val result = transfer(TransferRequest.Transfer(intent.type, intent.senderId, intent.receiverPan, intent.amount))
                    result.onSuccess {
                        directions.moveToTransferVerify()
                    }.onFailure {

                    }
                    reduce { state.copy(isLoading = false) }
                }
            }

            TransfersByPhoneNumberContract.Intent.GetCards -> {
                viewModelScope.launch {
                    getCards().onSuccess {
                        reduce { state.copy(cards = it, balance = it[0].amount) }
                    }.onFailure {

                    }
                }
            }
            TransfersByPhoneNumberContract.Intent.MoveToBack->{
                directions.moveToBack()
            }
        }
    }

    override val container =
        container<TransfersByPhoneNumberContract.UiState, TransfersByPhoneNumberContract.SideEffect>(TransfersByPhoneNumberContract.UiState())
}