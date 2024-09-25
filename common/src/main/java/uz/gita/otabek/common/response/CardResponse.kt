package uz.gita.otabek.common.response

import com.google.gson.annotations.SerializedName

sealed interface CardResponse {

    data object GetCards : ArrayList<CardItem>(), CardResponse {
        private fun readResolve(): Any = GetCards
    }

    data class CardItem(
        val id: Int,
        val name: String,
        val amount: Int,
        val owner: String,
        val pan: String,
        @SerializedName("expired-year")
        val expiredYear: Int,
        @SerializedName("expired-month")
        val expiredMonth: Int,
        @SerializedName("theme-type")
        val themeType: Int,
        val isVisible: Boolean
    ) : CardResponse

    data class AddCard(val message: String) : CardResponse

    data class UpdateCard(val message: String) : CardResponse

    data class DeleteCard(val message: String) : CardResponse
}