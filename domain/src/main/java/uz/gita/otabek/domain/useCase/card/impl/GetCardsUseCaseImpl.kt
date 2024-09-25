package uz.gita.otabek.domain.useCase.card.impl

import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.GetCardsUseCase
import javax.inject.Inject

class GetCardsUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : GetCardsUseCase {
    override fun invoke() = cardRepository.getCards()
}