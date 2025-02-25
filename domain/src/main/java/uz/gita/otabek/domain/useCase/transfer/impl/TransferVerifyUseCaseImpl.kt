package uz.gita.otabek.domain.useCase.transfer.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.TransferVerifyUseCase
import javax.inject.Inject

class TransferVerifyUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : TransferVerifyUseCase {
    override fun invoke(data: TransferRequest.TransferVerify): Flow<Result<Unit>> =
        flow { transferRepository.transferVerify(data).apply { emit(this) } }
}