package com.example.emsolution.back

import android.net.Uri

data class ProductData(
    val barcode : Int,
    val image: Uri?,
    val title: String?,
    val description: String?,
    val amount: Int,
    val price: Int
)