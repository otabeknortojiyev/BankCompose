package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignUpVerifyUseCase
import javax.inject.Inject

class SignUpVerifyUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignUpVerifyUseCase {
    override fun invoke(code: String): Flow<Result<Unit>> = flow { authRepository.signUpVerify(code).apply { emit(this) } }
}