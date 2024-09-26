package uz.gita.otabek.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.AuthApi
import uz.gita.otabek.data.network.AuthAuthenticator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        storage: LocalStorage,
        oAuthApiLazy: dagger.Lazy<AuthApi>
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .authenticator(AuthAuthenticator(storage, oAuthApiLazy))
        .addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .header("Authorization", "Bearer ${storage.accessToken}")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    @[Provides Singleton]
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://195.158.16.140/mobile-bank/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}