package uz.gita.otabek.domain.useCase.transfer.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetFeeUseCase
import javax.inject.Inject

class GetFeeUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetFeeUseCase {
    override fun invoke(data: TransferRequest.GetFee): Flow<Result<TransferResponse.GetFee>> =
        flow { transferRepository.getFee(data).apply { emit(this) } }
}