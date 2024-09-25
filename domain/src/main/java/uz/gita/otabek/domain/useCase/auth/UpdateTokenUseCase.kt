package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface UpdateTokenUseCase {
    operator fun invoke(data: AuthRequest.UpdateToken): Flow<Result<Unit>>
}