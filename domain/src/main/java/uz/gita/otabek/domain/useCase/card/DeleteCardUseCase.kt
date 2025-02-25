package uz.gita.otabek.domain.useCase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.CardRequest

interface DeleteCardUseCase {
    operator fun invoke(data: CardRequest.DeleteCard): Flow<Result<Unit>>
}