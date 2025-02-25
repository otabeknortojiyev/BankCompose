package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.GetPINUseCase
import javax.inject.Inject

class GetPINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : GetPINUseCase {
    override fun invoke(): Flow<Result<String>> = flow { authRepository.getPIN().apply { emit(this) } }
}