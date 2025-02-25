package uz.gita.otabek.domain.useCase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.CardRequest

interface AddCardUseCase {
    operator fun invoke(data: CardRequest.AddCard): Flow<Result<Unit>>
}