package com.example.emsolution.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.emsolution.back.Utils

class LoginViewModel() : ViewModel() {
    val fieldData = MutableLiveData<Boolean>()

    fun validateButton(userName: String, password: String) {
        fieldData.postValue(Utils.checkFieldsUser(userName, password))
    }

}
