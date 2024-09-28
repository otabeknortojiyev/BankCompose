package uz.gita.otabek.domain.useCase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse

interface UpdateInfoUseCase {
    suspend operator fun invoke(data: HomeRequest.UpdateInfo): Result<HomeResponse.UpdateInfo>
}