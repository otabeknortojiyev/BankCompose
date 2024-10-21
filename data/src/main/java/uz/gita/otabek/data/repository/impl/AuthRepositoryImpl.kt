package uz.gita.otabek.data.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.AuthApi
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.data.utils.toResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi, private val localStorage: LocalStorage
) : AuthRepository {
    private val gson = Gson()

    override suspend fun signUp(data: AuthRequest.SignUp): Result<Unit> = withContext(Dispatchers.IO) {
        api.signUp(data).toResult(gson) {
            localStorage.token = it.token
            Result.success(Unit)
        }
    }

    override suspend fun signUpVerify(code: String): Result<Unit> = withContext(Dispatchers.IO) {
        api.signUpVerify((AuthRequest.SignUpVerify(localStorage.token, code))).toResult(gson) {
            localStorage.accessToken = it.accessToken
            localStorage.refreshToken = it.refreshToken
            Result.success(Unit)
        }
    }

    override suspend fun signUpResend(): Result<Unit> = withContext(Dispatchers.IO) {
        api.signUpResend(AuthRequest.SignUpResend(localStorage.token)).toResult(gson) {
            localStorage.token = it.token
            Result.success(Unit)
        }
    }

    override suspend fun signIn(data: AuthRequest.SignIn): Result<Unit> = withContext(Dispatchers.IO) {
        api.signIn(data).toResult(gson) {
            localStorage.token = it.token
            Result.success(Unit)
        }
    }

    override suspend fun signInVerify(code: String): Result<Unit> = withContext(Dispatchers.IO) {
        api.signInVerify(AuthRequest.SignInVerify(localStorage.token, code)).toResult(gson) {
            localStorage.accessToken = it.accessToken
            localStorage.refreshToken = it.refreshToken
            Result.success(Unit)
        }
    }

    override suspend fun signInResend(): Result<Unit> = withContext(Dispatchers.IO) {
        api.signInResend(AuthRequest.SignInResend(localStorage.token)).toResult(gson) {
            localStorage.token = it.token
            Result.success(Unit)
        }
    }

    override suspend fun updateToken(data: AuthRequest.UpdateToken): Result<Unit> = withContext(Dispatchers.IO) {
        api.updateToken(data).toResult(gson) { Result.success(Unit) }
    }


    override suspend fun savePIN(pin: String): Result<Unit> = withContext(Dispatchers.IO) {
        localStorage.pin = pin
        Result.success(Unit)
    }

    override suspend fun setLanguage(lang: String): Result<Unit> = withContext(Dispatchers.IO) {
        localStorage.language = lang
        Result.success(Unit)
    }

    override suspend fun checkLanguage(): Result<String> = withContext(Dispatchers.IO) { Result.success(localStorage.language) }

    override suspend fun checkPIN(): Result<Boolean> = withContext(Dispatchers.IO) { Result.success(localStorage.pin.isNotEmpty()) }

    override suspend fun getPIN(): Result<String> = withContext(Dispatchers.IO) { Result.success(localStorage.pin) }
}