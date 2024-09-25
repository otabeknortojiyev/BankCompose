package uz.gita.otabek.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest

interface TransferRepository {
    fun getCardOwnerByPan(data: TransferRequest.GetCardOwnerByPan): Flow<Result<Unit>>
    fun getFee(data: TransferRequest.GetFee): Flow<Result<Unit>>
    fun transfer(data: TransferRequest.Transfer): Flow<Result<Unit>>
    fun transferVerify(data: TransferRequest.TransferVerify): Flow<Result<Unit>>
    fun getHistory(data: TransferRequest.GetHistory): Flow<Result<Unit>>
    fun transferResend(data: TransferRequest.TransferResend): Flow<Result<Unit>>
}