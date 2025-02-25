package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignInUseCase {
    override fun invoke(data: AuthRequest.SignIn): Flow<Result<Unit>> = flow { authRepository.signIn(data).apply { emit(this) } }
}