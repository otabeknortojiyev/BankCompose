package uz.gita.otabek.domain.useCase.transfer.impl

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.TransferResendUseCase
import javax.inject.Inject

class TransferResendUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : TransferResendUseCase {
    override suspend fun invoke(data: TransferRequest.TransferResend) = transferRepository.transferResend(data)
}