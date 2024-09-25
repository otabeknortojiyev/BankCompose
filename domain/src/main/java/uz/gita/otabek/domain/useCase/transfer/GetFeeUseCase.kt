package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest

interface GetFeeUseCase {
    operator fun invoke(data: TransferRequest.GetFee): Flow<Result<Unit>>
}