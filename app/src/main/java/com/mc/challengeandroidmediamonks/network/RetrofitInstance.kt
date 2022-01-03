package com.mc.challengeandroidmediamonks.network

import com.mc.challengeandroidmediamonks.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val constants = Constants()

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}