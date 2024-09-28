package uz.gita.otabek.domain.useCase.card

import uz.gita.otabek.common.request.CardRequest

interface AddCardUseCase {
    suspend operator fun invoke(data: CardRequest.AddCard): Result<Unit>
}