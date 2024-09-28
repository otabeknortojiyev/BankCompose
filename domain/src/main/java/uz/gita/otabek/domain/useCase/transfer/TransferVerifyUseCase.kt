package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest

interface TransferVerifyUseCase {
    suspend operator fun invoke(data: TransferRequest.TransferVerify): Result<Unit>
}