package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignUpUseCase {
    suspend operator fun invoke(data: AuthRequest.SignUp): Result<Unit>
}