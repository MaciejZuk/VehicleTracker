package com.maciejzuk.vehicletracking.di

import com.maciejzuk.vehicletracking.repository.remote.API_BASE_URL
import com.maciejzuk.vehicletracking.repository.remote.VehicleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor {
            val request: Request = it
                .request()
                .newBuilder()
                .addHeader("x-api-key", "fEQKqncokU4ASLelRK1ho9WzKpzi30q429bkwo5y")
                .build()
            it.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideVehicleAPI(retrofit: Retrofit): VehicleApiService {
        return retrofit.create(VehicleApiService::class.java)
    }
}