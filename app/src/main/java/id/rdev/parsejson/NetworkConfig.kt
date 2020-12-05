package id.rdev.parsejson

import id.rdev.parsejson.dataAdzan.ResultAdzan
import id.rdev.parsejson.dataAdzan.ResultAdzan2
import id.rdev.parsejson.dataAdzan.Timings
import id.rdev.parsejson.model.ApiSolatResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NetworkConfig {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.aladhan.com/v1/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Users::class.java)
}
interface Users {
    @GET("calendarByCity")
    fun getUsers(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: String,
        @Query("month") month: String,
        @Query("year") year: String
    ): Call<ApiSolatResponse>
}