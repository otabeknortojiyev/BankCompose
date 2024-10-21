package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.TransferApi
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.data.utils.toResult
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi, private val localStorage: LocalStorage
) : TransferRepository {
    private val gson = Gson()
    override suspend fun getCardOwnerByPan(data: TransferRequest.GetCardOwnerByPan): Result<TransferResponse.GetCardOwnerByPan> =
        withContext(Dispatchers.IO) {
            api.getCardOwnerByPan(data).toResult(gson) {
                Result.success(TransferResponse.GetCardOwnerByPan(it.pan))
            }
        }

    override suspend fun getFee(data: TransferRequest.GetFee): Result<TransferResponse.GetFee> = withContext(Dispatchers.IO) {
        api.getFee(data).toResult(gson) {
            Result.success(TransferResponse.GetFee(it.fee, it.amount))
        }
    }

    override suspend fun transfer(data: TransferRequest.Transfer): Result<TransferResponse.Transfer> = withContext(Dispatchers.IO) {
        api.transfer(data).toResult(gson) {
            Result.success(TransferResponse.Transfer(it.token))
        }
    }

    override suspend fun transferVerify(data: TransferRequest.TransferVerify): Result<Unit> = withContext(Dispatchers.IO) {
        api.transferVerify(data).toResult(gson) {
            Result.success(Unit)
        }
    }

    override suspend fun getHistory(data: TransferRequest.GetHistory): Result<TransferResponse.GetHistory> = withContext(Dispatchers.IO) {
        api.getHistory(data.size, data.currentPage).toResult(gson) {
            Result.success(
                TransferResponse.GetHistory(
                    it.totalElements, it.totalPages, it.currentPage, it.child
                )
            )
        }
    }

    override suspend fun transferResend(data: TransferRequest.TransferResend): Result<TransferResponse.TransferResend> = withContext(Dispatchers.IO) {
        api.transferResend(data).toResult(gson) {
            Result.success(TransferResponse.TransferResend(it.token))
        }
    }
}