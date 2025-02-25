package uz.gita.otabek.domain.useCase.card.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.domain.useCase.card.DeleteCardUseCase
import javax.inject.Inject

class DeleteCardUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) : DeleteCardUseCase {
    override fun invoke(data: CardRequest.DeleteCard): Flow<Result<Unit>> = flow { cardRepository.deleteCard(data).apply { emit(this) } }
}