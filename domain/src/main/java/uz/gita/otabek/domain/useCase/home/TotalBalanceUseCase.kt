package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.HomeResponse

interface TotalBalanceUseCase {
    suspend operator fun invoke(): Result<HomeResponse.TotalBalance>
}