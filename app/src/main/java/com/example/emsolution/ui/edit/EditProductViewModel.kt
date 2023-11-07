package com.example.emsolution.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore


class EditProductViewModel : ViewModel() {
    var amountData = MutableLiveData<Int>()
    /*
    fun updateOption(
        image: String,
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

        db.collection("productosTextil")
            .document(productIdBd)
            .update(newProduct)
            .addOnSuccessListener {
                onSuccess("success")
            }
            .addOnFailureListener { e ->
                onError("error : $e")
            }
    }
*/
    fun addOption(
        barcode: Int,
        image: String,
        title: String,
        description: String,
        amount: Int,
        price: Int,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ){
        val db = FirebaseFirestore.getInstance()
        val newProduct = hashMapOf(
            "barcode" to barcode,
            "imagen" to image,
            "titulo" to title,
            "descripcion" to description,
            "cantidad" to amount,
            "precio" to price
        )
        val nameCollection = "productosTextil"
        db.collection(nameCollection)
            .document()
            .set(newProduct)
            .addOnSuccessListener {
                onSuccess("success")
            }
            .addOnFailureListener { e ->
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