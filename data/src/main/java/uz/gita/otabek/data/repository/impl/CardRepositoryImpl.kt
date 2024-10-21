package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.common.response.CardResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.local.room.CardDataBase
import uz.gita.otabek.data.network.CardApi
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.data.utils.toResult
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi, private val storage: LocalStorage,
    private val dataBase: CardDataBase
) : CardRepository {
    private val gson = Gson()
    override suspend fun getCards(): Result<List<CardResponse.CardItem>> = withContext(Dispatchers.IO) {
        val cachedCards = dataBase.cardDao().getAllCards()
        if (cachedCards.isNotEmpty()) {
            launch {
                try {
                    val result = api.getCards()
                    if (result.isSuccessful && result.body() != null) {
                        dataBase.cardDao().insertCards(result.body()!!)
                    }
                } catch (e: Exception) {

                }
            }
            Result.success(cachedCards)
        }
        api.getCards().toResult(gson) {
            dataBase.cardDao().insertCards(it)
            Result.success(it)
        }
    }

    override suspend fun addCard(data: CardRequest.AddCard): Result<Unit> = withContext(Dispatchers.IO) {
        api.addCard(data).toResult(gson) { Result.success(Unit) }
    }

    override suspend fun updateCard(data: CardRequest.UpdateCard): Result<Unit> = withContext(Dispatchers.IO) {
        api.updateCard(data).toResult(gson) { Result.success(Unit) }
    }

    override suspend fun deleteCard(data: CardRequest.DeleteCard): Result<Unit> = withContext(Dispatchers.IO) {
        api.deleteCard(data.cardId).toResult(gson) { Result.success(Unit) }
    }
}