package uz.gita.otabek.domain.useCase.auth.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.CheckLanguageUseCase
import javax.inject.Inject

class CheckLanguageUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : CheckLanguageUseCase {
    override fun invoke(): Flow<Result<String>> = flow { authRepository.checkLanguage().apply { emit(this) } }
}