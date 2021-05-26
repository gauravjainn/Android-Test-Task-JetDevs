package com.loginkotlindemo.WebService

import retrofit2.Call
import retrofit2.http.POST

public interface APIService {
    @POST("api/login")
    abstract fun loginRequest(): Call<String>
}