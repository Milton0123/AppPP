package com.example.emsolution.ui.see_stock

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.emsolution.back.ProductData
import com.google.firebase.firestore.FirebaseFirestore

class SeeStockViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val nameCollection = "productosTextil"
    fun getProduct(onSuccess: (List<ProductData>) -> Unit, onFailure: (Any?) -> Unit) {
        db.collection(nameCollection)
            .get()
            .addOnSuccessListener { querySnapShot ->
                if (!querySnapShot.isEmpty) {
                    val productList = mutableListOf<ProductData>()

                    for (document in querySnapShot) {
                        val productBarcode = document.getLong("barcode")
                        val productImage = document.getString("imagen")
                        val productTitle = document.getString("titulo")
                        val productDescription = document.getString("descripcion")
                        val productAmount = document.getLong("cantidad")
                        val productPrice = document.getLong("precio")

                        val product = ProductData(
                            productBarcode.toString().toInt(),
                            productImage.toString().toUri(),
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
