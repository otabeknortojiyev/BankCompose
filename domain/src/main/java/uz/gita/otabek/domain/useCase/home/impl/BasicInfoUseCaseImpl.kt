package uz.gita.otabek.domain.useCase.home.impl

import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.BasicInfoUseCase
import javax.inject.Inject

class BasicInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : BasicInfoUseCase {
    override suspend fun invoke() = homeRepository.basicInfo()
}