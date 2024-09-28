package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SetLanguageUseCase {
    suspend operator fun invoke(lang: String): Result<Unit>
}