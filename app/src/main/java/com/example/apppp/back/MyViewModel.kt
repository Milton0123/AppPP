package com.example.apppp.back

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(repository: Repository) : ViewModel() {
    private val data = MutableLiveData<MutableList<ProductModel>>()

    val myRepository = Repository()

    fun requestProduct(products: ProductModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = myRepository.getListProducts()
            data.postValue(result)
        }
    }

    fun getMyListProduct(): MutableList<ProductModel> {
        return myRepository.getListProducts()
    }


}

class ViewModelFactory(private var myRepo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(myRepo) as T
    }
}
