package uz.gita.otabek.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse

interface HomeRepository {
    fun totalBalance(): Flow<Result<HomeResponse.TotalBalance>>
    fun basicInfo(): Flow<Result<HomeResponse.BasicInfo>>
    fun fullInfo(): Flow<Result<HomeResponse.FullInfo>>
    fun updateInfo(data: HomeRequest.UpdateInfo): Flow<Result<HomeResponse.UpdateInfo>>
    fun lastTransfers(): Flow<Result<HomeResponse.LastTransfers>>
}