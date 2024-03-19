package org.lotka.xenonx.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.lotka.xenonx.domain.util.Sektorum
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /*
    Non Chain Version
    */
    @Singleton
    @Named("delay")
    @Provides
    fun provideDelayInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()

                // Proceed with the original request
                val originalResponse = chain.proceed(originalRequest)

                // Introduce a 5-second delay
                Thread.sleep(1)

                return originalResponse
            }
        }
    }


    @Singleton
    @Named("header")
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithHeaders = originalRequest.newBuilder()
                .header("client", "android")
                // Add other headers here if needed
                .method(originalRequest.method, originalRequest.body)
                .build()
            chain.proceed(requestWithHeaders)
        }
    }

    @Singleton
    @Provides
    fun provideDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return dispatcher
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @Singleton
    @Named("AuthHttpClient")
    @Provides
    fun provideAuthHttpClient(
        logging: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ) = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(45, TimeUnit.SECONDS) // connect timeout
        .writeTimeout(45, TimeUnit.SECONDS) // write timeout
        .readTimeout(45, TimeUnit.SECONDS) // read timeout
        .retryOnConnectionFailure(true)
        .dispatcher(dispatcher)
        .build()


    @Sektorum
    @Provides
    fun provideRetrofit(@Named("AuthHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://m.kiliddev.ir/api/uaa/pro/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    @Singleton
    @Named("HomeHttpClient")
    @Provides
    fun provideHomeHttpClient(
        logging: HttpLoggingInterceptor,
     //   networkConnectionInterceptor: NetworkConnectionInterceptor,
     //   @Named("delay") delay: Interceptor,
        @Named("header") header: Interceptor,
        dispatcher: Dispatcher
    ) = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(header)
      //  .addInterceptor(delay)
        .connectTimeout(45, TimeUnit.SECONDS) // connect timeout
        .writeTimeout(45, TimeUnit.SECONDS) // write timeout
        .readTimeout(45, TimeUnit.SECONDS) // read timeout
        .retryOnConnectionFailure(true)
        .dispatcher(dispatcher)
        .build()

    @Sektorum
    @Provides
    fun homeApiService(@Named("HomeHttpClient") okHttpClient: OkHttpClient): org.lotka.xenonx.data.api.HomeService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://server.kilid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(org.lotka.xenonx.data.api.HomeService::class.java)

    @Singleton
    @Provides
    fun appInfoApiService(@Named("HomeHttpClient")  okHttpClient: OkHttpClient): org.lotka.xenonx.data.api.CdnService =
         Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(org.lotka.xenonx.BuildConfig.CDN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(org.lotka.xenonx.data.api.CdnService::class.java)


}


