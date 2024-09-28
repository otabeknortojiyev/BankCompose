package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignInResendUseCase {
    suspend operator fun invoke() : Result<Unit>
}