package uz.gita.otabek.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.common.response.CardResponse

interface CardRepository {
    fun getCards(): Flow<Result<CardResponse.GetCards>>
    fun addCard(data: CardRequest.AddCard): Flow<Result<Unit>>
    fun updateCard(data: CardRequest.UpdateCard): Flow<Result<Unit>>
    fun deleteCard(data: CardRequest.DeleteCard): Flow<Result<Unit>>
}