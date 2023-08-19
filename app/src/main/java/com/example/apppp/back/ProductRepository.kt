package com.example.apppp.back

import android.widget.Toast


class ProductRepository {
    /*
    VENTA
    revisar si la lista tiene el producto que yo quiero borrar
    en caso de tenerlo revisar que la cantidad de stock sea mayor o
    igual a la cantidad a eliminar

    RESULTADO DE LO VALIDADO
    devolver result <Product>

    posibles status:
    SUCCESS
    STOCK INSUFICIENTE
    PRODUCTO NO ENCONTRADO


    Cuando traiga ver stock omitir a los que tengan un stock de 0
fun sellProduct(product: String) {
}
    */
    fun validateUser(user: String): String {
        return when (user) {
            Users.admin.name -> "admin"
            Users.empleados.name -> "employee"
            else -> "not found"
        }
    }

    private fun validatePass(pass: String): String {
        return when (pass) {
            Users.admin.password -> "admin"
            Users.empleados.password -> "employee"
            else -> "not found"
        }
    }

    fun checkUserExist(user: String, pass: String): Boolean {
        return !((validateUser(user) == "not found" || validatePass(pass) == "not found") && ((validateUser(
            user
        ) != (validatePass(pass)))))
    }

    fun getList(): MutableList<ProductData> {
        return ProductBD.list
    }
    fun searchProduct(barcode: String):String{
        var messageSearch = ""
        var messageSearchError = ""
        ProductBD.list.forEach{
            if(barcode == it.barcode){
                messageSearch = it.name.toString()
                messageSearchError = "product success"
            }
        }
        if(messageSearchError != "product success"){
            messageSearch = "product error"
        }
        return messageSearch
    }

    fun findProductById(productId: String): ProductData? {
        return ProductBD.list.find { it.barcode == productId }
    }

    fun updateProduct(updatedProduct: ProductData) {
        val index = ProductBD.list.indexOfFirst { it.barcode == updatedProduct.barcode }
        if (index != -1) {
            val oldProduct = ProductBD.list[index]
            ProductBD.list[index] = updatedProduct.copy(barcode = oldProduct.barcode)
        }
    }
}
