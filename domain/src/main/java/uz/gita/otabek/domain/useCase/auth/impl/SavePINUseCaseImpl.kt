package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SavePINUseCase
import javax.inject.Inject

class SavePINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SavePINUseCase {
    override fun invoke(pin: String): Flow<Result<Unit>> = flow { authRepository.savePIN(pin).apply { emit(this) } }
}