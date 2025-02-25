package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SignUpResendUseCase {
    operator fun invoke(): Flow<Result<Unit>>
}