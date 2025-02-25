package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow


interface CheckLanguageUseCase {
    operator fun invoke(): Flow<Result<String>>
}