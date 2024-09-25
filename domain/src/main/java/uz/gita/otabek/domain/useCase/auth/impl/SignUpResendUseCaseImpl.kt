package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignUpResendUseCase
import javax.inject.Inject

class SignUpResendUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignUpResendUseCase {
    override fun invoke() = authRepository.signUpResend()
}