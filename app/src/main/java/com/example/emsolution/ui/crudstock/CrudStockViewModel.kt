package com.example.emsolution.ui.crudstock

import androidx.lifecycle.ViewModel
import com.example.emsolution.back.ProductData
import com.google.firebase.firestore.FirebaseFirestore

class CrudStockViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    fun getProduct(onSuccess: (List<ProductData>) -> Unit, onFailure: (Any?) -> Unit) {
        db.collection("productosTextil")
            .get()
            .addOnSuccessListener { querySnapShot ->
                if (!querySnapShot.isEmpty) {
                    val productList = mutableListOf<ProductData>()

                    for (document in querySnapShot) {
                        val productImage = document.getString("imagen")
                        val productTitle = document.getString("titulo")
                        val productDescription = document.getString("descripcion")
                        val productAmount = document.getLong("cantidad")
                        val productPrice = document.getLong("precio")

                        val product = ProductData(
                            productImage.toString(),
                            productTitle.toString(),
                            productDescription.toString(),
                            productAmount.toString().toInt(),
                            productPrice.toString().toInt()
                        )
                        productList.add(product)
                    }
                    onSuccess(productList)
                }
            }
            .addOnFailureListener{ e ->
            onFailure(e)
        }
    }
}