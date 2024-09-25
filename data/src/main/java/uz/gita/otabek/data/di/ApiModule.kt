package uz.gita.otabek.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.gita.otabek.data.network.AuthApi
import uz.gita.otabek.data.network.CardApi
import uz.gita.otabek.data.network.HomeApi
import uz.gita.otabek.data.network.TransferApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun providesAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun providesHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

    @[Provides Singleton]
    fun providesCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun providesTransferApi(retrofit: Retrofit): TransferApi = retrofit.create(TransferApi::class.java)
}