package uz.gita.otabek.domain.useCase.auth

import uz.gita.otabek.common.request.AuthRequest

interface UpdateTokenUseCase {
    suspend operator fun invoke(data: AuthRequest.UpdateToken): Result<Unit>
}