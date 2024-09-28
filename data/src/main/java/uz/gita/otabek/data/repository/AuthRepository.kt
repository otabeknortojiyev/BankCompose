package uz.gita.otabek.data.repository

import uz.gita.otabek.common.request.AuthRequest

interface AuthRepository {
    suspend fun signUp(data: AuthRequest.SignUp): Result<Unit>
    suspend fun signUpVerify(code: String): Result<Unit>
    suspend fun signIn(data: AuthRequest.SignIn): Result<Unit>
    suspend fun signInVerify(code: String): Result<Unit>
    suspend fun updateToken(data: AuthRequest.UpdateToken): Result<Unit>
    suspend fun signUpResend(): Result<Unit>
    suspend fun signInResend(): Result<Unit>
    suspend fun savePIN(pin: String): Result<Unit>
    suspend fun checkPIN(): Result<Boolean>
    suspend fun getPIN(): Result<String>
    suspend fun setLanguage(lang: String): Result<Unit>
    suspend fun checkLanguage(): Result<String>
}