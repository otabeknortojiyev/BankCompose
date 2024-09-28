package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.SetLanguageUseCase
import javax.inject.Inject

class SetLanguageUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SetLanguageUseCase {
    override suspend fun invoke(lang: String) = authRepository.setLanguage(lang)
}