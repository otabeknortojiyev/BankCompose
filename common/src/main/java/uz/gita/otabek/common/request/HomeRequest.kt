package uz.gita.otabek.common.request

import com.google.gson.annotations.SerializedName

sealed interface HomeRequest {

    data class UpdateInfo(
        @SerializedName("first-name")
        val firstName: String,
        @SerializedName("last-name")
        val lastName: String,
        @SerializedName("gender-type")
        val genderType: String,
        @SerializedName("born-date")
        val bornDate: String
    ) : HomeRequest
}