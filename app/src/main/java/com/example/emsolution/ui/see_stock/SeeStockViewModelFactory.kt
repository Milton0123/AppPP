package com.example.emsolution.ui.see_stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SeeStockViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeeStockViewModel() as T
    }
}