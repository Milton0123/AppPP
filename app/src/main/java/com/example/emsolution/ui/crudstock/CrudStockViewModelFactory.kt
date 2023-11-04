package com.example.emsolution.ui.crudstock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CrudStockViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CrudStockViewModel() as T
    }
}