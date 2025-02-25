package uz.gita.otabek.domain.useCase.home.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.domain.useCase.home.BasicInfoUseCase
import javax.inject.Inject

class BasicInfoUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : BasicInfoUseCase {
    override fun invoke(): Flow<Result<HomeResponse.BasicInfo>> = flow { homeRepository.basicInfo().apply { emit(this) } }
}