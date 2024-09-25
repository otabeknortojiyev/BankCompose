package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.HomeResponse

interface LastTransfersUseCase {
    operator fun invoke(): Flow<Result<HomeResponse.LastTransfers>>
}