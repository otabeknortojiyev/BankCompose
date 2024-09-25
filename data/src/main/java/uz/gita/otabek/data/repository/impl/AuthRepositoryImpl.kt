package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    override fun signUp(data: AuthRequest.SignUp): Flow<Result<Unit>> = flow {
        val result = api.signUp(data)
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))

        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun signUpVerify(code: String): Flow<Result<Unit>> = flow {
        val result = api.signUpVerify(AuthRequest.SignUpVerify(localStorage.token, code))
        if (result.isSuccessful && result.body() != null) {
            localStorage.accessToken = result.body()!!.accessToken
            localStorage.refreshToken = result.body()!!.refreshToken
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun signIn(data: AuthRequest.SignIn): Flow<Result<Unit>> = flow {
        val result = api.signIn(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun signInVerify(data: AuthRequest.SignInVerify): Flow<Result<Unit>> = flow {
        val result = api.signInVerify(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun updateToken(data: AuthRequest.UpdateToken): Flow<Result<Unit>> = flow {
        val result = api.updateToken(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun signUpResend(): Flow<Result<Unit>> = flow {
        val result = api.signUpResend(AuthRequest.SignUpResend(localStorage.token))
        if (result.isSuccessful && result.body() != null) {
            localStorage.token = result.body()!!.token
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun signInResend(data: AuthRequest.SignInResend): Flow<Result<Unit>> = flow {
        val result = api.signInResend(data)
        if (result.isSuccessful && result.body() != null) {
            emit(Result.success(Unit))
        } else if (result.errorBody() != null) {
            val error = gson.fromJson(result.errorBody()!!.string(), ErrorMessage::class.java)
            emit(Result.failure(Exception(error.message)))
        } else {
            emit(Result.failure(Throwable(result.message())))
        }
    }

    override fun savePIN(pin: String) {
        localStorage.pin = pin
    }

    override fun checkPIN(): Boolean {
        return localStorage.pin.isNotEmpty()
    }

    override fun getPIN(): String {
        return localStorage.pin
    }

    override fun setLanguage(lang: String) {
        localStorage.language = lang
    }

    override fun checkLanguage(): String {
        return localStorage.language
    }
}