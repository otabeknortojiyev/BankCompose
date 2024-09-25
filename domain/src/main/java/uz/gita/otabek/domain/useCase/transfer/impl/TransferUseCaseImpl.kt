package uz.gita.otabek.domain.useCase.transfer.impl

import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.TransferUseCase
import javax.inject.Inject

class TransferUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : TransferUseCase {
    override fun invoke(data: TransferRequest.Transfer) = transferRepository.transfer(data)
}