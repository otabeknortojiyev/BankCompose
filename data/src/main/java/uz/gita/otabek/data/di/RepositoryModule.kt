package uz.gita.otabek.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.otabek.data.local.LocalStorage
import uz.gita.otabek.data.network.AuthApi
import uz.gita.otabek.data.network.CardApi
import uz.gita.otabek.data.network.HomeApi
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.data.repository.impl.AuthRepositoryImpl
import uz.gita.otabek.data.repository.impl.CardRepositoryImpl
import uz.gita.otabek.data.repository.impl.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @[Provides Singleton]
    fun provideAuthRepository(api: AuthApi, localStorage: LocalStorage): AuthRepository = AuthRepositoryImpl(api, localStorage)

    @[Provides Singleton]
    fun provideHomeRepository(api: HomeApi, localStorage: LocalStorage): HomeRepository = HomeRepositoryImpl(api, localStorage)

    @[Provides Singleton]
    fun provideCardRepository(api: CardApi, localStorage: LocalStorage): CardRepository = CardRepositoryImpl(api, localStorage)
}