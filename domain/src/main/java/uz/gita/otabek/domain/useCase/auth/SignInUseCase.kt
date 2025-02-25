package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignInUseCase {
    operator fun invoke(data: AuthRequest.SignIn): Flow<Result<Unit>>
}