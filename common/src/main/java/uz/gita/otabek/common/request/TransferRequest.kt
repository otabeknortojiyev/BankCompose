package uz.gita.otabek.common.request

import com.google.gson.annotations.SerializedName

sealed interface TransferRequest {
    data class GetCardOwnerByPan(val pan: String) : TransferRequest

    data class GetFee(
        @SerializedName("sender-id")
        val senderId: String,
        val receiver: String,
        val amount: Int
    ) : TransferRequest

    data class Transfer(
        val type: String,
        @SerializedName("sender-id")
        val senderId: String,
        @SerializedName("receiver-pan")
        val receiverPan: String,
        val amount: Int
    ) : TransferRequest

    data class TransferVerify(
        val token: String,
        val code: String
    ) : TransferRequest

    data class TransferResend(val token: String) : TransferRequest

    data class GetHistory(val size: Int, val currentPage: Int) : TransferRequest
}