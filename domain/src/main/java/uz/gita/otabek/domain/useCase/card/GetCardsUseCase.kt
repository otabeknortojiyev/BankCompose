package uz.gita.otabek.domain.useCase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.response.CardResponse

interface GetCardsUseCase {
    suspend operator fun invoke(): Result<List<CardResponse.CardItem>>
}