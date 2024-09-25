package uz.gita.otabek.domain.useCase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.CardResponse

interface GetCardsUseCase {
    operator fun invoke(): Flow<Result<CardResponse.GetCards>>
}