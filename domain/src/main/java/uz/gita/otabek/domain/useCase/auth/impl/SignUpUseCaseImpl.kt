package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignUpUseCase {
    override fun invoke(data: AuthRequest.SignUp) = authRepository.signUp(data)
}