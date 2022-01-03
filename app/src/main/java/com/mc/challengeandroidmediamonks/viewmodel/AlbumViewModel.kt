package com.mc.challengeandroidmediamonks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mc.challengeandroidmediamonks.model.Album
import com.mc.challengeandroidmediamonks.network.ApiService
import com.mc.challengeandroidmediamonks.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel  : ViewModel() {
    private val recyclerListLiveData : MutableLiveData<ArrayList<Album>> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<ArrayList<Album>> {
        return recyclerListLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance().create(ApiService::class.java)
            val response = retroInstance.albumList()
            recyclerListLiveData.postValue(response)
        }
    }
}