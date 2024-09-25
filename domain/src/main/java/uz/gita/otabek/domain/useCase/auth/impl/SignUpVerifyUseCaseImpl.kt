package uz.gita.otabek.domain.useCase.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignUpVerifyUseCase
import javax.inject.Inject

class SignUpVerifyUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignUpVerifyUseCase {
    override fun invoke(code:String) = authRepository.signUpVerify(code)
}