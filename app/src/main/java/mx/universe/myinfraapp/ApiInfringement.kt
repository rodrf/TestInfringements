package mx.universe.myinfraapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

open class ApiInfringement {
        companion object {
            private val interceptor = HttpLoggingInterceptor()
        }

        init {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        fun getCustomService(): GetAPIService {
            val customClient = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
            /* Enable interceptor only in debug mode */
            if (BuildConfig.DEBUG) {
                customClient.addInterceptor(interceptor)
            }
            val clientBuilder = customClient.build()
            val API_URL = "http://18.191.14.236:8080/api/"
            val builder = Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(clientBuilder).build()
            return builder.create(GetAPIService::class.java)
        }

        interface GetAPIService {

            @Headers ("token:d/vfEi6hDQULP0gtnoiri9HiSk5zP27i2Ryj5eiK7GM=")
            @GET("infringement/get-infringements-to-date/")
            fun getImplicatedDetail(@QueryMap params: Map<String, String>): Call<ResponseInfringements>

        }
    }
