package uz.gita.otabek.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.otabek.data.local.room.CardDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun provideCardDatabase(@ApplicationContext context: Context): CardDataBase =
        Room.databaseBuilder(context = context, CardDataBase::class.java, "Database.db")
            .build()
}