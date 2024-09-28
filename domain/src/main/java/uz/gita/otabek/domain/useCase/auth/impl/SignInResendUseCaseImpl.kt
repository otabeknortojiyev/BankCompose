package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInResendUseCase
import javax.inject.Inject

class SignInResendUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :SignInResendUseCase {
    override suspend fun invoke() = authRepository.signInResend()
}