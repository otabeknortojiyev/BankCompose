package uz.gita.otabek.common.request

import com.google.gson.annotations.SerializedName

sealed interface CardRequest {
    data class AddCard(
        val pan: String,
        @SerializedName("expired-year")
        val expiredYear: String,
        @SerializedName("expired-month")
        val expiredMonth: String,
        val name: String
    ) : CardRequest

    data class UpdateCard(
        val id: Int,
        val name: String,
        @SerializedName("theme-type")
        val themeType: Int,
        @SerializedName("is-visible")
        val isVisible: String
    ) : CardRequest

    data class DeleteCard(
        val cardId: Int
    ) : CardRequest
}