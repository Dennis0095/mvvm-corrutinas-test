package com.example.retotecnico.presentation.view.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retotecnico.core.LocationUser

class ViewModelMaps : ViewModel() {

    val updateLocation_ = MutableLiveData<LocationUser>()


    fun updateLocation(location: LocationUser){
        updateLocation_.postValue(location)
    }

}