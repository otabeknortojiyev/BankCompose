package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.HomeRequest
import uz.gita.otabek.common.response.HomeResponse
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.HomeApi
import uz.gita.otabek.data.repository.HomeRepository
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
        val result = api.totalBalance()
        if (result.isSuccessful && result.body() != null) {
            val totalBalance = result.body()!!.totalBalance
            storage.totalBalance = totalBalance
            Result.success(HomeResponse.TotalBalance(totalBalance))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
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
        val result = api.basicInfo()
        if (result.isSuccessful && result.body() != null) {
            val name = result.body()!!.firstName
            val genderType = result.body()!!.genderType
            val age = result.body()!!.age
            storage.userName = name
            storage.genderType = genderType
            storage.userAge = age
            Result.success(HomeResponse.BasicInfo(name, genderType, age))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun fullInfo(): Result<HomeResponse.FullInfo> = withContext(Dispatchers.IO) {
        val result = api.fullInfo()
        if (result.isSuccessful && result.body() != null) {
            Result.success(
                HomeResponse.FullInfo(
                    result.body()!!.firstName, result.body()!!.lastName, result.body()!!.phone, result.body()!!.bornDate, result.body()!!.gender
                )
            )
        } else if (result.errorBody() != null) {
//            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            Result.failure(Exception("Nimadur xato"))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun updateInfo(data: HomeRequest.UpdateInfo): Result<HomeResponse.UpdateInfo> = withContext(Dispatchers.IO) {
        val result = api.updateInfo(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(HomeResponse.UpdateInfo(result.body()!!.message))
        } else if (result.errorBody() != null) {
//            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            Result.failure(Exception("Nimadur xato"))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun lastTransfers(): Result<HomeResponse.LastTransfers> = withContext(Dispatchers.IO) {
        val result = api.lastTransfers()
        if (result.isSuccessful && result.body() != null) {
            Result.success(HomeResponse.LastTransfers)
        } else if (result.errorBody() != null) {
//            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            Result.failure(Exception("Nimadur xato"))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }
}