package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignInVerifyUseCase {
    operator fun invoke(data: AuthRequest.SignInVerify): Flow<Result<Unit>>
}