package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInVerifyUseCase
import javax.inject.Inject

class SignInVerifyUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignInVerifyUseCase {
    override fun invoke(data: AuthRequest.SignInVerify) = authRepository.signInVerify(data)
}