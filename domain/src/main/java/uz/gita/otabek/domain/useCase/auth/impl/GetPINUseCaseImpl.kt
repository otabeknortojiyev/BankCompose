package uz.gita.otabek.domain.useCase.auth.impl

import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.domain.useCase.auth.GetPINUseCase
import javax.inject.Inject

class GetPINUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : GetPINUseCase{
    override fun invoke(): String {
        return authRepository.getPIN()
    }
}