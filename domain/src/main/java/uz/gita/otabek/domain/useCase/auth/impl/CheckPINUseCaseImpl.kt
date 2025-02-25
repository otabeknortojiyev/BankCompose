package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.CheckPINUseCase
import javax.inject.Inject

class CheckPINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : CheckPINUseCase {
    override fun invoke(): Flow<Result<Boolean>> = flow { authRepository.checkPIN().apply { emit(this) } }
}