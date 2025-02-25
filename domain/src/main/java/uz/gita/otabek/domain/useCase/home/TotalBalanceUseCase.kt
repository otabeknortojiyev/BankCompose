package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.HomeResponse

interface TotalBalanceUseCase {
    operator fun invoke(): Flow<Result<HomeResponse.TotalBalance>>
}