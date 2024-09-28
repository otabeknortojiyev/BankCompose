package uz.gita.otabek.domain.useCase.home.impl

import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.UpdateInfoUseCase
import javax.inject.Inject

class UpdateInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : UpdateInfoUseCase {
    override suspend fun invoke(data: HomeRequest.UpdateInfo) = homeRepository.updateInfo(data)
}