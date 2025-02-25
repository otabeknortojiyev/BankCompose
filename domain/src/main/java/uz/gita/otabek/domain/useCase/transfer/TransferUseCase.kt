package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse

interface TransferUseCase {
    operator fun invoke(data: TransferRequest.Transfer): Flow<Result<TransferResponse.Transfer>>
}