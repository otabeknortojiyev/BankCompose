package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.TransferApi
import uz.gita.otabek.data.repository.TransferRepository
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi,
    private val localStorage: LocalStorage
) : TransferRepository {
    private val gson = Gson()
    override fun getCardOwnerByPan(data: TransferRequest.GetCardOwnerByPan): Flow<Result<Unit>> = flow {
        val result = api.getCardOwnerByPan(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun getFee(data: TransferRequest.GetFee): Flow<Result<Unit>> = flow {
        val result = api.getFee(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun transfer(data: TransferRequest.Transfer): Flow<Result<Unit>> = flow {
        val result = api.transfer(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun transferVerify(data: TransferRequest.TransferVerify): Flow<Result<Unit>> = flow {
        val result = api.transferVerify(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun getHistory(data: TransferRequest.GetHistory): Flow<Result<Unit>> = flow {
        val result = api.getHistory(data.size, data.currentPage)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun transferResend(data: TransferRequest.TransferResend): Flow<Result<Unit>> = flow {
        val result = api.transferResend(data)
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