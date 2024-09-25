package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    override fun totalBalance(): Flow<Result<HomeResponse.TotalBalance>> = flow {
        val result = api.totalBalance()
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(HomeResponse.TotalBalance(result.body()!!.totalBalance)))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            emit(Result.failure(Exception("Nimadur xato")))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun basicInfo(): Flow<Result<HomeResponse.BasicInfo>> = flow {
        val result = api.basicInfo()
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(HomeResponse.BasicInfo(result.body()!!.firstName, result.body()!!.genderType, result.body()!!.age)))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
//            emit(Result.failure(Exception(error.message)))
            emit(Result.failure(Exception("Nimadur xato")))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun fullInfo(): Flow<Result<HomeResponse.FullInfo>> = flow {
        val result = api.fullInfo()
        if (result.isSuccessful && result.body() != null) {
            emit(
                Result.success(
                    HomeResponse.FullInfo(
                        result.body()!!.firstName, result.body()!!.lastName, result.body()!!.phone, result.body()!!.bornDate, result.body()!!.gender
                    )
                )
            )
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun updateInfo(data: HomeRequest.UpdateInfo): Flow<Result<HomeResponse.UpdateInfo>> = flow {
        val result = api.updateInfo(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(HomeResponse.UpdateInfo(result.body()!!.message)))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun lastTransfers(): Flow<Result<HomeResponse.LastTransfers>> = flow {
        val result = api.lastTransfers()
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(HomeResponse.LastTransfers))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }
}