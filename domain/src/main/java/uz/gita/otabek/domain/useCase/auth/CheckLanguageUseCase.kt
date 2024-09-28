package uz.gita.otabek.domain.useCase.auth


interface CheckLanguageUseCase {
    suspend operator fun invoke(): Result<String>
}