package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SignInVerifyUseCase {
    suspend operator fun invoke(code: String): Result<Unit>
}