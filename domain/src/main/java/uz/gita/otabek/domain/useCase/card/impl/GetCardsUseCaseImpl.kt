package uz.gita.otabek.domain.useCase.card.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.response.CardResponse
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.GetCardsUseCase
import javax.inject.Inject

class GetCardsUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : GetCardsUseCase {
    override fun invoke(): Flow<Result<List<CardResponse.CardItem>>> = flow { cardRepository.getCards().apply { emit(this) } }
}