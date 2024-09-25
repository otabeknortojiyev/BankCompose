package uz.gita.otabek.domain.useCase.transfer.impl

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.TransferVerifyUseCase
import javax.inject.Inject

class TransferVerifyUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : TransferVerifyUseCase {
    override fun invoke(data: TransferRequest.TransferVerify) = transferRepository.transferVerify(data)
}