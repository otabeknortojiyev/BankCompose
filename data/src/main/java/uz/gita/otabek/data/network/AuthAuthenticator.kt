package uz.gita.otabek.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.data.local.LocalStorage
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
    private val localStorage: LocalStorage,
    private val oAuthApi: AuthApi,
//    private val appRepository: AppRepository,
//    private val direction: EntryPointDirection
) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking { localStorage.accessToken }
        synchronized(this) {
            val updatedToken = runBlocking { localStorage.accessToken }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { oAuthApi.updateToken(AuthRequest.UpdateToken(localStorage.refreshToken)) }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let { body ->
                        runBlocking {
                            localStorage.accessToken = body.accessToken
                            localStorage.refreshToken = body.refreshToken
                        }
                        body.accessToken
                    }
                } else null
            }
//            if (token == null) {
//                runBlocking {
//                    appRepository.clearUserData()
//                    direction.logout()
//                }
//            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}