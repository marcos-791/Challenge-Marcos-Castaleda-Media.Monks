package com.mc.challengeandroidmediamonks.network

import com.mc.challengeandroidmediamonks.model.Album
import com.mc.challengeandroidmediamonks.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Para filtrar busqueda
    /*@GET("albums")
    suspend fun getDataFromApi(@Query("q")query: String) : ArrayList<Album>*/

    //Despliega la lista Album
    @GET("albums")
    suspend fun albumList() : ArrayList<Album>

    //Despliega la lista de la búsqueda del nombre y/o Id en el Album
    @GET("albums/{id}/photos")
    suspend fun photoList(@Path(value = "id", encoded = true) id : Int) : ArrayList<Photo>

}