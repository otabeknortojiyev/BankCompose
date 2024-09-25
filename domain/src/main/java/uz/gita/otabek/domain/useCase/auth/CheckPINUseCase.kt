package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface CheckPINUseCase {
    operator fun invoke() : Boolean
}