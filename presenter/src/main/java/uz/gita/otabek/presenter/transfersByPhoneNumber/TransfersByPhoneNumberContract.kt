package uz.gita.otabek.presenter.transfersByPhoneNumber

import org.orbitmvi.orbit.ContainerHost
import uz.gita.otabek.common.response.CardResponse

interface TransfersByPhoneNumberContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false, val receiverName: String = "", val fee: Int = 0, val amountWithFee: Int = 0, val balance: Int = 100000,
//        val cards: List<CardResponse.CardItem> = emptyList(),
        val cards: List<CardResponse.CardItem> = arrayListOf(
            CardResponse.CardItem(0, "", 100000, "", "9860160607296771", 2024, 12, 1, true),
            CardResponse.CardItem(1, "", 200000, "", "9860160607296772", 2025, 11, 1, true),
            CardResponse.CardItem(2, "", 300000, "", "9860160607296773", 2026, 10, 1, true),
            CardResponse.CardItem(3, "", 400000, "", "9860160607296774", 2027, 12, 1, true),
            CardResponse.CardItem(4, "", 500000, "", "9860160607296775", 2028, 12, 1, true)
        ), val error: String = "", val senderPan: String = "", val type: String = ""
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToTransferVerify()
        suspend fun moveToBack()
    }

    interface Intent {
        data class MakeTransfer(val type: String, val senderId: String, val receiverPan: String, val amount: Int) : Intent
        data class GetCardOwnerByPan(val senderId: String, val receiverPan: String, val amount: Int) : Intent
        data class GetFee(val senderId: String, val receiver: String, val amount: Int) : Intent
        data object GetCards : Intent
        data object MoveToBack : Intent
    }
}