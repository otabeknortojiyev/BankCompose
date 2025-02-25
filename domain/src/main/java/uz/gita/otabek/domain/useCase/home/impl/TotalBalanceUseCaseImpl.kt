package uz.gita.otabek.domain.useCase.home.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.TotalBalanceUseCase
import javax.inject.Inject

class TotalBalanceUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : TotalBalanceUseCase {
    override fun invoke(): Flow<Result<HomeResponse.TotalBalance>> = flow { homeRepository.totalBalance().apply { emit(this) } }
}