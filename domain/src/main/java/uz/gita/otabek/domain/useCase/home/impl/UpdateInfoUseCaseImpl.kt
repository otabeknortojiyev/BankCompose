package uz.gita.otabek.domain.useCase.home.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.UpdateInfoUseCase
import javax.inject.Inject

class UpdateInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : UpdateInfoUseCase {
    override fun invoke(data: HomeRequest.UpdateInfo): Flow<Result<HomeResponse.UpdateInfo>> =
        flow { homeRepository.updateInfo(data).apply { emit(this) } }
}