package uz.gita.otabek.common.request

import com.google.gson.annotations.SerializedName

sealed interface AuthRequest {
    data class SignUp(
        val phone: String,
        val password: String,
        @SerializedName("first-name")
        val firstName: String,
        @SerializedName("last-name")
        val lastName: String,
        @SerializedName("born-date")
        val bornDate: String,
        val gender: String
    ) : AuthRequest

    data class SignUpVerify(
        val token: String,
        val code: String
    ) : AuthRequest

    data class SignIn(
        val phone: String,
        val password: String
    ) : AuthRequest

    data class SignInVerify(
        val token: String,
        val code: String
    ) : AuthRequest

    data class UpdateToken(
        @SerializedName("refresh-token")
        val refreshToken: String
    ) : AuthRequest

    data class SignUpResend(val token: String) : AuthRequest

    data class SignInResend(val token: String) : AuthRequest
}