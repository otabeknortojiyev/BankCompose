package uz.gita.otabek.domain.useCase.transfer.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetCardOwnerByPanUseCase
import javax.inject.Inject

class GetCardOwnerByPanUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetCardOwnerByPanUseCase {
    override fun invoke(data: TransferRequest.GetCardOwnerByPan) = transferRepository.getCardOwnerByPan(data)
}