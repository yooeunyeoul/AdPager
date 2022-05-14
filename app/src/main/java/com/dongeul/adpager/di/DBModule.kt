package com.dongeul.adpager.di

import android.content.Context
import androidx.room.Room
import com.dongeul.adpager.db.AppDatabase
import com.dongeul.adpager.db.PagerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "campaign.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePagerDao(appDatabase: AppDatabase):PagerDao{
        return appDatabase.pagerDao()
    }
}