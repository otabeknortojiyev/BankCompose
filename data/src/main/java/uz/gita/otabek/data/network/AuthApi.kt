package uz.gita.otabek.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.common.response.AuthResponse

interface AuthApi {

    @POST("v1/auth/sign-up")
    suspend fun signUp(@Body data: AuthRequest.SignUp): Response<AuthResponse.SignUp>

    @POST("v1/auth/sign-up/verify")
    suspend fun signUpVerify(@Body data: AuthRequest.SignUpVerify): Response<AuthResponse.SignUpVerify>

    @POST("v1/auth/sign-in")
    suspend fun signIn(@Body data: AuthRequest.SignIn): Response<AuthResponse.SignIn>

    @POST("v1/auth/sign-in/verify")
    suspend fun signInVerify(@Body data: AuthRequest.SignInVerify): Response<AuthResponse.SignInVerify>

    @POST("v1/auth/update-token")
    suspend fun updateToken(@Body data: AuthRequest.UpdateToken): Response<AuthResponse.UpdateToken>

    @POST("v1/auth/sign-up/resend")
    suspend fun signUpResend(@Body data: AuthRequest.SignUpResend): Response<AuthResponse.SignUpResend>

    @POST("v1/auth/sign-in/resend")
    suspend fun signInResend(@Body data: AuthRequest.SignInResend): Response<AuthResponse.SignInResend>

}