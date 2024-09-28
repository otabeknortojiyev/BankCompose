package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SignUpVerifyUseCase {
    suspend operator fun invoke(code: String): Result<Unit>
}