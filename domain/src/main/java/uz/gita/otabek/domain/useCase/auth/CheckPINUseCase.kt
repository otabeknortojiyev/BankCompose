package uz.gita.otabek.domain.useCase.auth

interface CheckPINUseCase {
    suspend operator fun invoke(): Result<Boolean>
}