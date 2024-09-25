package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.CheckLanguageUseCase
import javax.inject.Inject

class CheckLanguageUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : CheckLanguageUseCase {
    override fun invoke(): String = authRepository.checkLanguage()
}