package com.example.emsolution.ui.crudstock


import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.emsolution.back.ProductData
import com.google.firebase.firestore.FirebaseFirestore

class CrudStockViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val nameCollecttion = "productosTextil"
    fun getProduct(onSuccess: (List<ProductData>) -> Unit, onFailure: (Any?) -> Unit) {
        db.collection(nameCollecttion)
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

    fun removeProduct(itemTrashBarcode : Int, onSuccess : () -> Unit, onFailure: (String) -> Any){
        db.collection(nameCollecttion)
            .whereEqualTo("barcode", itemTrashBarcode)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    val nameDocumentEdit = documentSnapshot.id
                    db.collection(nameCollecttion)
                        .document(nameDocumentEdit)
                        .delete()
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure("no se ha podido remover su producto, error: $e")
                        }
                }else{
                    onFailure("No se encontró el producto con el barcode proporcionado.")
                }
            }
            .addOnFailureListener{ e ->

            }
    }
}