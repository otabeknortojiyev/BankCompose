package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface GetPINUseCase {
    suspend operator fun invoke(): Result<String>
}