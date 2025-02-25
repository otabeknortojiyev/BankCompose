package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.UpdateTokenUseCase
import javax.inject.Inject

class UpdateTokenUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : UpdateTokenUseCase {
    override fun invoke(data: AuthRequest.UpdateToken): Flow<Result<Unit>> = flow { authRepository.updateToken(data).apply { emit(this) } }
}