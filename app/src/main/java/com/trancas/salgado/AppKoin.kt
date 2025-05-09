package com.trancas.salgado

import android.app.Application
import com.trancas.salgado.service.LoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppKoin : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@AppKoin)

            modules(appModule)
        }
    }
}
object SalgadoApi {
    private val BASE_URL = "http://10.0.2.2:8080/api/";

    fun getApi(token: String): LoginService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LoginService::class.java)
    }
}