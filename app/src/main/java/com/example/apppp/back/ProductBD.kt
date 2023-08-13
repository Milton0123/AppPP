package com.example.apppp.back

object ProductBD {
    val list = mutableListOf<ProductData>()

    fun add_product(producto: ProductData) {
        list.add(producto)
    }
}

