package uz.gita.otabek.domain.useCase.auth

import uz.gita.otabek.common.request.AuthRequest
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    operator fun invoke(data: AuthRequest.SignUp): Flow<Result<Unit>>
}