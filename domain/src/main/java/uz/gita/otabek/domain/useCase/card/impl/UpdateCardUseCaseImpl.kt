package uz.gita.otabek.domain.useCase.card.impl

import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.UpdateCardUseCase
import javax.inject.Inject

class UpdateCardUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : UpdateCardUseCase {
    override fun invoke(data: CardRequest.UpdateCard) = cardRepository.updateCard(data)
}