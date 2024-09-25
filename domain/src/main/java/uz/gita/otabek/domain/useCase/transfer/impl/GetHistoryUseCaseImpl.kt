package uz.gita.otabek.domain.useCase.transfer.impl

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetHistoryUseCase
import javax.inject.Inject

class GetHistoryUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetHistoryUseCase {
    override fun invoke(data: TransferRequest.GetHistory) = transferRepository.getHistory(data)
}