package uz.gita.otabek.domain.useCase.transfer.impl

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetFeeUseCase
import javax.inject.Inject

class GetFeeUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetFeeUseCase {
    override fun invoke(data: TransferRequest.GetFee) = transferRepository.getFee(data)
}