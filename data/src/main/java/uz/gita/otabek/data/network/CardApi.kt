package uz.gita.otabek.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.gita.otabek.common.request.CardRequest
import uz.gita.otabek.common.response.CardResponse

interface CardApi {

    @GET("v1/card")
    suspend fun getCards(): Response<List<CardResponse.CardItem>>

    @POST("v1/card")
    suspend fun addCard(@Body data: CardRequest.AddCard): Response<CardResponse.AddCard>

    @PUT("v1/card")
    suspend fun updateCard(@Body data: CardRequest.UpdateCard): Response<CardResponse.UpdateCard>

    @DELETE("v1/card/{id}")
    suspend fun deleteCard(@Path("id") cardId: Int): Response<CardResponse.DeleteCard>
}