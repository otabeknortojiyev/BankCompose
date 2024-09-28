package uz.gita.otabek.domain.useCase.card.impl

import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.AddCardUseCase
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : AddCardUseCase {
    override suspend fun invoke(data: CardRequest.AddCard) = cardRepository.addCard(data)
}