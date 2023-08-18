package com.example.apppp.back

object ProductBD {
    val list = mutableListOf<ProductData>(
        ProductData(
            "1",
            "sabanas",
            3000,
            10
        ),
        ProductData(
            "2",
            "cortinas",
            4000,
            9
        ),
        ProductData(
            "3",
            "acolchados",
            9000,
            28
        ),
        ProductData(
            "4",
            "alfombras",
            2900,
            92
        ),
        ProductData(
            "5",
            "almohadas",
            3360,
            12
        ),
        ProductData(
            "6",
            "repasadores",
            436,
            35
        )
    )

    fun findProductById(productId: String): ProductData? {
        return list.find { it.barcode == productId }
    }

    fun updateProduct(updatedProduct: ProductData) {
        val index = list.indexOfFirst { it.barcode == updatedProduct.barcode }
        if (index != -1) {
            val oldProduct = list[index]
            list[index] = updatedProduct.copy(barcode = oldProduct.barcode)
        }
    }

}

