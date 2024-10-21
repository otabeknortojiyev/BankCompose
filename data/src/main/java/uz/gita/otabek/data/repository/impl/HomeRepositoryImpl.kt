package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.HomeApi
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.data.utils.toResult
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi, private val storage: LocalStorage
) : HomeRepository {
    private val gson = Gson()
    override suspend fun totalBalance(): Result<HomeResponse.TotalBalance> = withContext(Dispatchers.IO) {
        val cachedTotalBalance: Int = storage.totalBalance
        if (cachedTotalBalance == 0) {
            launch {
                try {
                    val result = api.totalBalance()
                    if (result.isSuccessful && result.body() != null) {
                        storage.totalBalance = result.body()!!.totalBalance
                    }
                } catch (e: Exception) {

                }
            }
            Result.success(HomeResponse.TotalBalance(cachedTotalBalance))
        }
        api.totalBalance().toResult(gson) {
            storage.totalBalance = it.totalBalance
            Result.success(HomeResponse.TotalBalance(it.totalBalance))
        }
    }

    override suspend fun basicInfo(): Result<HomeResponse.BasicInfo> = withContext(Dispatchers.IO) {
        val cachedName: String = storage.userName
        val cachedGenderType: Int = storage.genderType
        val cachedAge: Int = storage.userAge
        if (cachedName.isNotEmpty()) {
            launch {
                try {
                    val result = api.basicInfo()
                    if (result.isSuccessful && result.body() != null) {
                        storage.userName = result.body()!!.firstName
                        storage.genderType = result.body()!!.genderType
                        storage.userAge = result.body()!!.age
                    }
                } catch (e: Exception) {

                }

            }
            Result.success(HomeResponse.BasicInfo(cachedName, cachedGenderType, cachedAge))
        }
        api.basicInfo().toResult(gson) {
            storage.userName = it.firstName
            storage.genderType = it.genderType
            storage.userAge = it.age
            Result.success(HomeResponse.BasicInfo(it.firstName, it.genderType, it.age))
        }
    }

    override suspend fun fullInfo(): Result<HomeResponse.FullInfo> = withContext(Dispatchers.IO) {
        api.fullInfo().toResult(gson) {
            Result.success(HomeResponse.FullInfo(it.firstName, it.lastName, it.phone, it.bornDate, it.gender))
        }
    }

    override suspend fun updateInfo(data: HomeRequest.UpdateInfo): Result<HomeResponse.UpdateInfo> = withContext(Dispatchers.IO) {
        api.updateInfo(data).toResult(gson) {
            Result.success(HomeResponse.UpdateInfo(it.message))
        }
    }

    override suspend fun lastTransfers(): Result<HomeResponse.LastTransfers> = withContext(Dispatchers.IO) {
        api.lastTransfers().toResult(gson) {
            Result.success(it)
        }
    }
}