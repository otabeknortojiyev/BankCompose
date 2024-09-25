package uz.gita.otabek.domain.useCase.auth

import kotlinx.coroutines.flow.Flow

interface SavePINUseCase {
    operator fun invoke(pin: String)
}