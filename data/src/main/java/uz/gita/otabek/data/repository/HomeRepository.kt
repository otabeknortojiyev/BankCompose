package uz.gita.otabek.data.repository

import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse

interface HomeRepository {
    suspend fun totalBalance(): Result<HomeResponse.TotalBalance>
    suspend fun basicInfo(): Result<HomeResponse.BasicInfo>
    suspend fun fullInfo(): Result<HomeResponse.FullInfo>
    suspend fun updateInfo(data: HomeRequest.UpdateInfo): Result<HomeResponse.UpdateInfo>
    suspend fun lastTransfers(): Result<HomeResponse.LastTransfers>
}