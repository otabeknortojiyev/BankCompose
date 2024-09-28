package uz.gita.otabek.domain.useCase.transfer

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse

interface GetCardOwnerByPanUseCase {
    suspend operator fun invoke(data: TransferRequest.GetCardOwnerByPan): Result<TransferResponse.GetCardOwnerByPan>
}