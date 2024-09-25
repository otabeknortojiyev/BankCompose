package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignInResendUseCase {
    operator fun invoke(data: AuthRequest.SignInResend) : Flow<Result<Unit>>
}