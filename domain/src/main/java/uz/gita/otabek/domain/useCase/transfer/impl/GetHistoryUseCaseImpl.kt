package uz.gita.otabek.domain.useCase.transfer.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse
import uz.gita.otabek.data.repository.TransferRepository
import uz.gita.otabek.domain.useCase.transfer.GetHistoryUseCase
import javax.inject.Inject

class GetHistoryUseCaseImpl @Inject constructor(private val transferRepository: TransferRepository) : GetHistoryUseCase {
    override fun invoke(data: TransferRequest.GetHistory): Flow<Result<TransferResponse.GetHistory>> =
        flow { transferRepository.getHistory(data).apply { emit(this) } }
}