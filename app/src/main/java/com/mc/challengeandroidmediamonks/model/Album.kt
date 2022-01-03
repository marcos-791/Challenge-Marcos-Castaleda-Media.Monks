package com.mc.challengeandroidmediamonks.model

import com.google.gson.annotations.SerializedName

data class Album (
    @SerializedName("userId") var idUser : String,
    @SerializedName("id") var id : String,
    @SerializedName("title") var title : String)