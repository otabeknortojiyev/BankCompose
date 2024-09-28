package uz.gita.otabek.domain.useCase.home.impl

import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.FullInfoUseCase
import javax.inject.Inject

class FullInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : FullInfoUseCase {
    override suspend fun invoke() = homeRepository.fullInfo()
}