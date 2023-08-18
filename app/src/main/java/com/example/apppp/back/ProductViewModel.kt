package com.example.apppp.back

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {
    val fieldData = MutableLiveData<Boolean>()
    val userData = MutableLiveData<Boolean>()
    val barcodeData = MutableLiveData<String>()
    val fieldDataAmount = MutableLiveData<ProductData>()

    fun validateButton(userName: String, password: String) {
        fieldData.postValue(Utils.checkFields(userName, password))
    }

    fun checkUsers(user: String, password: String) {
        userData.postValue(repository.checkUserExist(user, password))
    }

    fun getListProducts(): MutableList<ProductData>{
        return repository.getList()
    }
    fun validateSearch(barcode: String){
        return barcodeData.postValue(repository.searchProduct(barcode))
    }

    fun checkField(field : Int, barcode: String){
        fieldDataAmount.postValue(Utils.checkField(field,barcode))
    }
}
