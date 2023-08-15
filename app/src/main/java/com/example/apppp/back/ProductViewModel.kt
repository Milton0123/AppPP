package com.example.apppp.back

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {
    val fieldData = MutableLiveData<Boolean>()
    val userData = MutableLiveData<Boolean>()

    fun validateButton(userName: String, password: String) {
        fieldData.postValue(Utils.checkFields(userName, password))
    }

    fun checkUsers(user: String, password: String) {
        userData.postValue(repository.checkUserExist(user, password))
    }

}
