package uz.gita.otabek.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.otabek.data.repository.AuthRepository
import uz.gita.otabek.data.repository.CardRepository
import uz.gita.otabek.data.repository.HomeRepository
import uz.gita.otabek.data.repository.impl.AuthRepositoryImpl
import uz.gita.otabek.data.repository.impl.CardRepositoryImpl
import uz.gita.otabek.data.repository.impl.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @[Binds Singleton]
    fun provideCardRepository(impl: CardRepositoryImpl): CardRepository
}