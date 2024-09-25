package uz.gita.otabek.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse

interface HomeApi {

    @GET("v1/home/total-balance")
    suspend fun totalBalance(): Response<HomeResponse.TotalBalance>

    @GET("v1/home/user-info")
    suspend fun basicInfo(): Response<HomeResponse.BasicInfo>

    @GET("v1/home/user-info/details")
    suspend fun fullInfo(): Response<HomeResponse.FullInfo>

    @PUT("v1/home/user-info")
    suspend fun updateInfo(@Body data: HomeRequest.UpdateInfo): Response<HomeResponse.UpdateInfo>

    @GET("v1/home/last-transfers")
    suspend fun lastTransfers(): Response<HomeResponse.LastTransfers>

}