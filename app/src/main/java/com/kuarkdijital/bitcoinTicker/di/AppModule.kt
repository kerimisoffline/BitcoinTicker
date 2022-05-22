package com.kuarkdijital.bitcoinTicker.di

import com.kuarkdijital.bitcoinTicker.data.BaseApi
import com.kuarkdijital.bitcoinTicker.data.repo.DefaultMainRepository
import com.kuarkdijital.bitcoinTicker.data.repo.MainRepository
import com.kuarkdijital.bitcoinTicker.util.DispactherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.coingecko.com/api/v3/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi():BaseApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BaseApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api:BaseApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispactherProvider = object : DispactherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}