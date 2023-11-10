package com.example.emsolution.ui.edit

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore


class EditProductViewModel : ViewModel() {
    var amountData = MutableLiveData<Int>()

    fun findNameDocument(barcodeDocument : Int, documentEdit : (String) -> Any) {
        val db = FirebaseFirestore.getInstance()
        val nameCollection = "productosTextil"
        db.collection(nameCollection)
            .whereEqualTo("barcode", barcodeDocument)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    val nameDocumentEdit = documentSnapshot.id
                    documentEdit(nameDocumentEdit)
                }
            }
            .addOnFailureListener { e ->
                documentEdit(e.toString())
            }
    }

    fun updateOption(
        image: Uri?,
        title: String,
        description: String,
        amount: Int,
        price: Int,
        productIdBd: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ){
        val db = FirebaseFirestore.getInstance()
        val newProduct: MutableMap<String, Any?> = hashMapOf(
            "imagen" to image,
            "titulo" to title,
            "descripcion" to description,
            "cantidad" to amount,
            "precio" to price
        )
        val nameCollection = "productosTextil"
        db.collection(nameCollection)
            .document(productIdBd)
            .update(newProduct)
            .addOnSuccessListener {
                onSuccess("success")
            }
            .addOnFailureListener { e ->
                onError("error : $e")
            }
    }

    fun addOption(
        barcode: Int,
        image: String,
        title: String,
        description: String,
        amount: Int,
        price: Int,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val db = FirebaseFirestore.getInstance()
        val nameCollection = "productosTextil"

        // Verificar si ya existe un documento con el mismo barcode
        db.collection(nameCollection)
            .whereEqualTo("barcode", barcode)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    // No hay documentos con el mismo barcode, puedes agregar uno nuevo
                    val newProduct = hashMapOf(
                        "barcode" to barcode,
                        "imagen" to image,
                        "titulo" to title,
                        "descripcion" to description,
                        "cantidad" to amount,
                        "precio" to price
                    )

                    // Agregar el nuevo producto
                    db.collection(nameCollection)
                        .document()
                        .set(newProduct)
                        .addOnSuccessListener {
                            onSuccess("success")
                        }
                        .addOnFailureListener { e ->
                            onError(e.toString())
                        }
                } else {
                    onError("Ya existe un producto con el mismo barcode")
                }
            }
            .addOnFailureListener { e ->
                // Manejar errores en la consulta
                onError(e.toString())
            }
    }



    fun addAmount( fieldAmount : Int = 0, amount: String){
        var fieldAmountData = fieldAmount
        when(amount){
            "mas"->{ fieldAmountData +=  1 }
            "menos"->{fieldAmountData -= 1}
        }
        if(fieldAmountData < 0){
            fieldAmountData = 0
        }
        amountData.postValue(fieldAmountData)
    }

}