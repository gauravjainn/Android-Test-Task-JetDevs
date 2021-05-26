package com.loginkotlindemo.WebService

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.loginkotlindemo.Repository.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

public class APIServiceFactory {
    private fun providesOkHttpClientBuilder(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        return httpClient.readTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.MINUTES).build()

    }

    fun loginWebService(): LiveData<LoginResponse> {
        val data = MutableLiveData<LoginResponse>()
        var webserviceResponseList: LoginResponse
        try {
            val retrofit = Retrofit.Builder()
                    .baseUrl(APIURL.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build()

            //Defining retrofit api service
            val service = retrofit.create(APIService::class.java)
            service.loginRequest().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("Repository", "Response::::" + response.body()!!)
                    webserviceResponseList = Gson().fromJson(
                        response.body(),
                        LoginResponse::class.java
                    )
                    data.setValue(webserviceResponseList)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Repository", "Failed:::")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data

    }
}