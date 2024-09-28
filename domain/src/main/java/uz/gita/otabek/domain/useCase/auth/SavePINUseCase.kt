package uz.gita.otabek.domain.useCase.auth

interface SavePINUseCase {
    suspend operator fun invoke(pin: String): Result<Unit>
}