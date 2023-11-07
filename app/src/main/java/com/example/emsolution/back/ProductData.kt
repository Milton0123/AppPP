package com.example.emsolution.back


data class ProductData(
    val barcode : Int,
    val image: String,
    val title: String,
    val description: String,
    val amount: Int,
    val price: Int
)
