package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInResendUseCase
import javax.inject.Inject

class SignInResendUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignInResendUseCase {
    override fun invoke(): Flow<Result<Unit>> = flow { authRepository.signInResend().apply { emit(this) } }
}