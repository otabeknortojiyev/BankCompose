package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.HomeResponse

interface BasicInfoUseCase {
    operator fun invoke(): Flow<Result<HomeResponse.BasicInfo>>
}