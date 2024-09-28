package uz.gita.otabek.common.response

import com.google.gson.annotations.SerializedName

sealed interface TransferResponse {
    data class GetCardOwnerByPan(val pan: String) : TransferResponse

    data class GetFee(val fee: Int, val amount: Int) : TransferResponse

    data class Transfer(val token: String) : TransferResponse

    data class TransferVerify(val message: String) : TransferResponse

    data class TransferResend(val token: String) : TransferResponse

    data class GetHistory(
        @SerializedName("total-elements")
        val totalElements: Int,
        @SerializedName("total-pages")
        val totalPages: Int,
        @SerializedName("current-page")
        val currentPage: Int,
        val child: List<HistoryItem>
    ) : TransferResponse

    data class HistoryItem(
        val type: String,
        val to: String,
        val from: String,
        val amount: Int,
        val time: Int
    ) : TransferResponse
}