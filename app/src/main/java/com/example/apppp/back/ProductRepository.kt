package com.example.apppp.back

import android.widget.Toast

class ProductRepository {
    fun sellProduct(product: String) {
        /*
        revisar si la lista tiene el producto que yo quiero borrar
        en caso de tenerlo revisar que la cantidad de stock sea mayor o
        igual a la cantidad a eliminar
        devolver result <Product>

        posibles status:
        SUCCESS
        STOCK INSUFICIENTE
        PRODUCTO NO ENCONTRADO





        Cuando traiga ver stock omitir a los que tengan un stock de 0
        */

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
}