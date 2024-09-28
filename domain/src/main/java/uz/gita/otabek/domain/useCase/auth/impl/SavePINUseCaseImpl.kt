package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SavePINUseCase
import javax.inject.Inject

class SavePINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SavePINUseCase {
    override suspend fun invoke(pin: String) = authRepository.savePIN(pin)
}