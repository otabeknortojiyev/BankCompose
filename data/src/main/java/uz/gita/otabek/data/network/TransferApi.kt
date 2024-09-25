package uz.gita.otabek.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.otabek.common.request.TransferRequest
import uz.gita.otabek.common.response.TransferResponse

interface TransferApi {

    @POST("v1/transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body data: TransferRequest.GetCardOwnerByPan): Response<TransferResponse.GetCardOwnerByPan>

    @POST("v1/transfer/fee")
    suspend fun getFee(@Body data: TransferRequest.GetFee): Response<TransferResponse.GetFee>

    @POST("v1/transfer/transfer")
    suspend fun transfer(@Body data: TransferRequest.Transfer): Response<TransferResponse.Transfer>

    @POST("v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body data: TransferRequest.TransferVerify): Response<TransferResponse.TransferVerify>

    @GET("v1/transfer/history")
    suspend fun getHistory(@Query("size") size: Int, @Query("current-page") currentPage: Int): Response<TransferResponse.GetHistory>

    @POST("v1/transfer/transfer/resend")
    suspend fun transferResend(@Body data: TransferRequest.TransferResend): Response<TransferResponse.TransferResend>

}