package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.common.response.CardResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.local.room.CardDataBase
import uz.gita.otabek.data.network.CardApi
import uz.gita.otabek.data.repository.CardRepository
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
        val result = api.getCards()
        if (result.isSuccessful && result.body() != null) {
            val cards = result.body()!!
            dataBase.cardDao().insertCards(cards)
            Result.success(cards)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }


    override suspend fun addCard(data: CardRequest.AddCard): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.addCard(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun updateCard(data: CardRequest.UpdateCard): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.updateCard(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun deleteCard(data: CardRequest.DeleteCard): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.deleteCard(data.cardId)
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }
}