package com.example.apppp.back


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
    /*
    private fun addProduct() {
        val supplierId = binding.crudEtIdProveedor.text.toString().toInt()
        val productName = binding.crudEtNameProduct.text.toString()
        val productPrice = binding.crudEtPriceProduct.text.toString().toInt()
        val productQuantity = binding.crudEtQuantityProduct.text.toString().toInt()
        val productDataActivity =
            ProductData(supplierId, productName, productPrice, productQuantity)
        ProductBD.addProduct(productDataActivity)

        if (supplierId != null && !productName.isNullOrEmpty() && productPrice != null && productQuantity != null) {
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
            clear()

            println("Contenido de la lista después de agregar el producto:")
            for (product in ProductBD.list)
                println(product)

        } else {
            Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
        }

    }

    private fun deleteProduct() {
        val productId = binding.crudEdIdProduct.text.toString().toIntOrNull()

        if (productId != null) {
            delete(productId)
        } else {
            Toast.makeText(this, "ID de producto inválido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun delete(id: Int) {

        val productDelete = ProductBD.list.find { it.id == id }

        if (productDelete != null) {
            ProductBD.list.remove(productDelete)
            Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show()

            println("Contenido de la lista después de eliminar el producto:")
            for (product in ProductBD.list)
                println(product)

            clearId()
        } else {
            Toast.makeText(this, "No se ha encontrado el producto, ID invalido", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun clear() {
        binding.crudEtIdProveedor.text.clear()
        binding.crudEtNameProduct.text.clear()
        binding.crudEtPriceProduct.text.clear()
        binding.crudEtQuantityProduct.text.clear()
    }

    private fun clearId() {
        binding.crudEdIdProduct.text.clear()
    }
*/

}
