package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.AuthApi
import uz.gita.otabek.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi, private val localStorage: LocalStorage
) : AuthRepository {
    private val gson = Gson()
    override suspend fun signUp(data: AuthRequest.SignUp): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signUp(data)
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun signUpVerify(code: String): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signUpVerify(AuthRequest.SignUpVerify(localStorage.token, code))
        if (result.isSuccessful && result.body() != null) {
            localStorage.accessToken = result.body()!!.accessToken
            localStorage.refreshToken = result.body()!!.refreshToken
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun signIn(data: AuthRequest.SignIn): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signIn(data)
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun signInVerify(code: String): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signInVerify(AuthRequest.SignInVerify(localStorage.token, code))
        if (result.isSuccessful && result.body() != null) {
            localStorage.accessToken = result.body()!!.accessToken
            localStorage.refreshToken = result.body()!!.refreshToken
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun updateToken(data: AuthRequest.UpdateToken): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.updateToken(data)
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun signUpResend(): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signUpResend(AuthRequest.SignUpResend(localStorage.token))
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun signInResend(): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.signInResend(AuthRequest.SignInResend(localStorage.token))
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            Result.success(Unit)
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            Result.failure(Exception(error.message))
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun savePIN(pin: String): Result<Unit> = withContext(Dispatchers.IO) {
        localStorage.pin = pin
        Result.success(Unit)
    }

    override suspend fun checkPIN(): Result<Boolean> = withContext(Dispatchers.IO) {
        Result.success(localStorage.pin.isNotEmpty())
    }

    override suspend fun getPIN(): Result<String> = withContext(Dispatchers.IO) {
        Result.success(localStorage.pin)
    }

    override suspend fun setLanguage(lang: String): Result<Unit> = withContext(Dispatchers.IO) {
        localStorage.language = lang
        Result.success(Unit)
    }

    override suspend fun checkLanguage(): Result<String> = withContext(Dispatchers.IO) {
        Result.success(localStorage.language)
    }
}