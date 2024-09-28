package uz.gita.otabek.domain.useCase.card

import uz.gita.otabek.common.request.CardRequest

interface UpdateCardUseCase {
    suspend operator fun invoke(data: CardRequest.UpdateCard): Result<Unit>
}