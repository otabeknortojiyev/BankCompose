package uz.gita.otabek.domain.useCase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse

interface TransferResendUseCase {
    operator fun invoke(data: TransferRequest.TransferResend): Flow<Result<TransferResponse.TransferResend>>
}