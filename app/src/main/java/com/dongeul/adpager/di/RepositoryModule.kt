package com.dongeul.adpager.di

import com.dongeul.adpager.db.PagerDao
import com.dongeul.adpager.network.ApiService
import com.dongeul.adpager.network.HttpRequestInterceptor
import com.dongeul.adpager.repository.PagerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(
        dao: PagerDao,
        service: ApiService,
    ): PagerRepository {
        return PagerRepository(dao,service)
    }

}