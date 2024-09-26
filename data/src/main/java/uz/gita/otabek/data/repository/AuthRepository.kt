package uz.gita.otabek.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.otabek.common.request.AuthRequest

interface AuthRepository {
    fun signUp(data: AuthRequest.SignUp): Flow<Result<Unit>>
    fun signUpVerify(code: String): Flow<Result<Unit>>
    fun signIn(data: AuthRequest.SignIn): Flow<Result<Unit>>
    fun signInVerify(code:String): Flow<Result<Unit>>
    fun updateToken(data: AuthRequest.UpdateToken): Flow<Result<Unit>>
    fun signUpResend(): Flow<Result<Unit>>
    fun signInResend(): Flow<Result<Unit>>
    fun savePIN(pin: String)
    fun checkPIN(): Boolean
    fun getPIN(): String
    fun setLanguage(lang: String)
    fun checkLanguage(): String
}