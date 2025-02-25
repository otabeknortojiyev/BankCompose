package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SignUpVerifyUseCase {
    operator fun invoke(code: String): Flow<Result<Unit>>
}