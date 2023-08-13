package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apppp.back.ProductBD
import com.example.apppp.back.ProductData
import com.example.apppp.databinding.ActivityAbmProductBinding

class abm_product_activity : AppCompatActivity() {

    private lateinit var binding: ActivityAbmProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbmProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crudProductIvBack.setOnClickListener { volver() }
        binding.crudBtAdd.setOnClickListener { add() }
    }

    fun add(){
        val supplier_id = binding.crudEtIdProveedor.text.toString().toInt()
        val product_name = binding.crudEtNameProduct.text.toString()
        val product_price = binding.crudEtPriceProduct.text.toString().toInt()
        val product_quantity = binding.crudEtQuantityProduct.text.toString().toInt()

        val producto = ProductData(supplier_id,product_name,product_price,product_quantity)
        ProductBD.add_product(producto)

        if (supplier_id != null && !product_name.isNullOrEmpty() && product_price != null && product_quantity != null) {
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
            clear()

            println("Contenido de la lista después de agregar el producto:")
            for (product in ProductBD.list)
                println(product)

        } else {
            Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
        }

    }

    fun clear(){
        binding.crudEtIdProveedor.text.clear()
        binding.crudEtNameProduct.text.clear()
        binding.crudEtPriceProduct.text.clear()
        binding.crudEtQuantityProduct.text.clear()
    }

    fun volver(){
        val back = Intent(this, admin_activity::class.java)
        startActivity(back)
    }
}