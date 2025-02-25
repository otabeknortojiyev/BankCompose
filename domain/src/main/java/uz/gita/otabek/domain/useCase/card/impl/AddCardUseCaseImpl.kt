package uz.gita.otabek.domain.useCase.card.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.AddCardUseCase
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : AddCardUseCase {
    override fun invoke(data: CardRequest.AddCard): Flow<Result<Unit>> = flow { cardRepository.addCard(data).apply { emit(this) } }
}