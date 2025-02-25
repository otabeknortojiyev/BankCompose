package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SetLanguageUseCase
import javax.inject.Inject

class SetLanguageUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SetLanguageUseCase {
    override fun invoke(lang: String): Flow<Result<Unit>> = flow { authRepository.setLanguage(lang).apply { emit(this) } }
}