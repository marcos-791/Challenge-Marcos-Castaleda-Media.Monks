package com.mc.challengeandroidmediamonks.model

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("albumId") var idAlbum : String,
    @SerializedName("id") var id : String,
    @SerializedName("title") var title : String,
    @SerializedName("url") var urlPhoto : String,
    @SerializedName("thumbnailUrl") var thumbnailUrl : String)