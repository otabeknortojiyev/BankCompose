package uz.gita.otabek.data.repository

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse

interface TransferRepository {
    suspend fun getCardOwnerByPan(data: TransferRequest.GetCardOwnerByPan): Result<TransferResponse.GetCardOwnerByPan>
    suspend fun getFee(data: TransferRequest.GetFee): Result<TransferResponse.GetFee>
    suspend fun transfer(data: TransferRequest.Transfer): Result<TransferResponse.Transfer>
    suspend fun transferVerify(data: TransferRequest.TransferVerify): Result<Unit>
    suspend fun getHistory(data: TransferRequest.GetHistory): Result<TransferResponse.GetHistory>
    suspend fun transferResend(data: TransferRequest.TransferResend): Result<TransferResponse.TransferResend>
}