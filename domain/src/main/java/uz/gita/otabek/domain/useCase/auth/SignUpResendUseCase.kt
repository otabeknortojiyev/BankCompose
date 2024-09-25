package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface SignUpResendUseCase {
    operator fun invoke(): Flow<Result<Unit>>
}