package com.example.emsolution.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditProductViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditProductViewModel() as T
    }
}