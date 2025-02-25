package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInVerifyUseCase
import javax.inject.Inject

class SignInVerifyUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignInVerifyUseCase {
    override fun invoke(code: String): Flow<Result<Unit>> = flow { authRepository.signInVerify(code).apply { emit(this) } }
}