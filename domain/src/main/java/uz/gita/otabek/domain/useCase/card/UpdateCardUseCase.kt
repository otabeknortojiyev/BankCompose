package uz.gita.otabek.domain.useCase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.CardRequest

interface UpdateCardUseCase {
    operator fun invoke(data: CardRequest.UpdateCard): Flow<Result<Unit>>
}