package uz.gita.otabek.common.response

import com.google.gson.annotations.SerializedName

sealed interface HomeResponse {

    data class TotalBalance(
        @SerializedName("total-balance")
        val totalBalance: Int
    ) : HomeResponse

    data class BasicInfo(
        @SerializedName("firsrt-name")
        val firstName: String,
        @SerializedName("gender-type")
        val genderType: Int,
        val age: Int
    ) : HomeResponse

    data class FullInfo(
        @SerializedName("first-name")
        val firstName: String,
        @SerializedName("last-name")
        val lastName: String,
        val phone: String,
        @SerializedName("born-date")
        val bornDate: Int,
        val gender: Int
    ) : HomeResponse

    data class UpdateInfo(val message: String) : HomeResponse

    data object LastTransfers : ArrayList<TransferItem>(), HomeResponse {
        private fun readResolve(): Any = LastTransfers
    }

    data class TransferItem(
        val type: String,
        val from: String,
        val to: String,
        val amount: Int,
        val time: Int
    )
}