package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.HomeResponse

interface BasicInfoUseCase {
    suspend operator fun invoke(): Result<HomeResponse.BasicInfo>
}