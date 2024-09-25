package uz.gita.otabek.domain.useCase.home.impl

import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.LastTransfersUseCase
import javax.inject.Inject

class LastTransfersUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : LastTransfersUseCase {
    override fun invoke() = homeRepository.lastTransfers()
}