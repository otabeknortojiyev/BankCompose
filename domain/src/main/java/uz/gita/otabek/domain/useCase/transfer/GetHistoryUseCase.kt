package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest

interface GetHistoryUseCase {
    operator fun invoke(data: TransferRequest.GetHistory): Flow<Result<Unit>>
}