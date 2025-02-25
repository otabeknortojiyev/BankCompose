package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest

interface TransferVerifyUseCase {
    operator fun invoke(data: TransferRequest.TransferVerify): Flow<Result<Unit>>
}