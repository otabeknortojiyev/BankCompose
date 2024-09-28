package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignInUseCase {
    override suspend fun invoke(data: AuthRequest.SignIn)  = authRepository.signIn(data)
}