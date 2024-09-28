package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.TransferApi
import uz.gita.otabek.data.repository.TransferRepository
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi, private val localStorage: LocalStorage
) : TransferRepository {
    private val gson = Gson()
    override suspend fun getCardOwnerByPan(data: TransferRequest.GetCardOwnerByPan): Result<TransferResponse.GetCardOwnerByPan> =
        withContext(Dispatchers.IO) {
            val result = api.getCardOwnerByPan(data)
            if (result.isSuccessful && result.body() != null) {
                Result.success(TransferResponse.GetCardOwnerByPan(result.body()!!.pan))
            } else if (result.errorBody() != null) {
                val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
                Result.failure(Exception(error.message))
            } else {
                Result.failure(Throwable(result.message()))
            }
        }

    override suspend fun getFee(data: TransferRequest.GetFee): Result<TransferResponse.GetFee> = withContext(Dispatchers.IO) {
        val result = api.getFee(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(TransferResponse.GetFee(result.body()!!.fee, result.body()!!.amount))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun transfer(data: TransferRequest.Transfer): Result<TransferResponse.Transfer> = withContext(Dispatchers.IO) {
        val result = api.transfer(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(TransferResponse.Transfer(result.body()!!.token))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun transferVerify(data: TransferRequest.TransferVerify): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.transferVerify(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun getHistory(data: TransferRequest.GetHistory): Result<TransferResponse.GetHistory> = withContext(Dispatchers.IO) {
        val result = api.getHistory(data.size, data.currentPage)
        if (result.isSuccessful && result.body() != null) {
            Result.success(
                TransferResponse.GetHistory(
                    result.body()!!.totalElements, result.body()!!.totalPages, result.body()!!.currentPage, result.body()!!.child
                )
            )
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun transferResend(data: TransferRequest.TransferResend): Result<TransferResponse.TransferResend> = withContext(Dispatchers.IO) {
        val result = api.transferResend(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(TransferResponse.TransferResend(result.body()!!.token))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }
}