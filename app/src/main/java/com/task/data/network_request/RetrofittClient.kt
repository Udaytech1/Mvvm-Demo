package com.task.data.network_request

import com.task.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*Singolton class to get retrofit instant for network call*/
object RetrofitClient {

    fun getInstance(): Retrofit {
        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        var mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer Wookie2019").build()
                chain.proceed(request)
            }
            .build()

        var retrofit: Retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(AppConstants.API_BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return retrofit
    }

    fun getApiServiceInstant(): ApiService{
        return getInstance().create(ApiService::class.java)
    }

}