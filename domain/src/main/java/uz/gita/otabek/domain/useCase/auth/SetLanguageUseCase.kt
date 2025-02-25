package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SetLanguageUseCase {
    operator fun invoke(lang: String): Flow<Result<Unit>>
}