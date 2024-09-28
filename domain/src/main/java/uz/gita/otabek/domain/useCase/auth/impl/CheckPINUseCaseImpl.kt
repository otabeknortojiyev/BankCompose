package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.CheckPINUseCase
import javax.inject.Inject

class CheckPINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : CheckPINUseCase {
    override suspend fun invoke() = authRepository.checkPIN()
}