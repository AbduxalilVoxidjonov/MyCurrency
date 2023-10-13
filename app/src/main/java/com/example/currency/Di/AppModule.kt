package com.example.currency.Di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.currency.Data.ConverterApi
import com.example.currency.Main.MainRepository
import com.example.currency.Main.MainRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getConverterApi(): ConverterApi {
        return Retrofit
            .Builder()
            .baseUrl("https://api.apilayer.com/currency_data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConverterApi::class.java)
    }
    @Singleton
    @Provides
    fun getMainRepository(api: ConverterApi): MainRepository = MainRepositoryImpl(api)

}