package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignUpResendUseCase
import javax.inject.Inject

class SignUpResendUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignUpResendUseCase {
    override fun invoke(): Flow<Result<Unit>> = flow { authRepository.signUpResend().apply { emit(this) } }
}