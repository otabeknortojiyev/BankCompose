package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface GetPINUseCase {
    operator fun invoke(): Flow<Result<String>>
}