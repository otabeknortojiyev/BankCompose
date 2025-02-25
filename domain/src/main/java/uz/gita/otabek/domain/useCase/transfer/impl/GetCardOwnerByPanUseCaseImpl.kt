package uz.gita.otabek.domain.useCase.transfer.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetCardOwnerByPanUseCase
import javax.inject.Inject

class GetCardOwnerByPanUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetCardOwnerByPanUseCase {
    override fun invoke(data: TransferRequest.GetCardOwnerByPan): Flow<Result<TransferResponse.GetCardOwnerByPan>> =
        flow { transferRepository.getCardOwnerByPan(data).apply { emit(this) } }
}