package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun getCards(): Flow<Result<List<CardResponse.CardItem>>> = flow {
        val list = dataBase.cardDao().getAllCards()
        emit(Result.success(list))
        delay(5000)
        val result = api.getCards()
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(result.body()!!))
            dataBase.cardDao().insertCards(result.body()!!)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            emit(Result.failure(Exception("Nimadur xato")))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }.flowOn(Dispatchers.IO)

    override fun addCard(data: CardRequest.AddCard): Flow<Result<Unit>> = flow {
        val result = api.addCard(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun updateCard(data: CardRequest.UpdateCard): Flow<Result<Unit>> = flow {
        val result = api.updateCard(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun deleteCard(data: CardRequest.DeleteCard): Flow<Result<Unit>> = flow {
        val result = api.deleteCard(data.cardId)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }
}