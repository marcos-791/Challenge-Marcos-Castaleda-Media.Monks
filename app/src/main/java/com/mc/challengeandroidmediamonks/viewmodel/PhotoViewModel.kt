package com.mc.challengeandroidmediamonks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mc.challengeandroidmediamonks.model.Photo
import com.mc.challengeandroidmediamonks.network.ApiService
import com.mc.challengeandroidmediamonks.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val recyclerListLiveData : MutableLiveData<ArrayList<Photo>> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<ArrayList<Photo>> {
        return recyclerListLiveData
    }

    fun makeApiCall(listNumber : Int){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance().create(ApiService::class.java)
            val response = retroInstance.photoList(listNumber)
            recyclerListLiveData.postValue(response)
        }
    }
}