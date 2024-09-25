package uz.gita.otabek.domain.useCase.home.impl

import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.TotalBalanceUseCase
import javax.inject.Inject

class TotalBalanceUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : TotalBalanceUseCase {
    override fun invoke() = homeRepository.totalBalance()
}