package uz.gita.otabek.data.repository

import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.common.response.CardResponse

interface CardRepository {
    suspend fun getCards(): Result<List<CardResponse.CardItem>>
    suspend fun addCard(data: CardRequest.AddCard): Result<Unit>
    suspend fun updateCard(data: CardRequest.UpdateCard): Result<Unit>
    suspend fun deleteCard(data: CardRequest.DeleteCard): Result<Unit>
}