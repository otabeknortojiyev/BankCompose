package uz.gita.otabek.domain.useCase.home.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.FullInfoUseCase
import javax.inject.Inject

class FullInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : FullInfoUseCase {
    override fun invoke(): Flow<Result<HomeResponse.FullInfo>> = flow { homeRepository.fullInfo().apply { emit(this) } }
}