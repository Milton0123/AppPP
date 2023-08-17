package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivityAbmProductBinding

class AbmProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbmProductBinding
    private lateinit var viewModel: ProductViewModel
    private var repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbmProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

        onClicks()
    }
    private fun initViewModel(){
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }
    private fun initRecyclerView(list : MutableList<ProductData>){
        val adapter = ProductAdapter(list)
        binding.crudRvProduct.adapter = adapter
    }
    private fun onClicks() {
        binding.crudProductIvBack.setOnClickListener { backActivity() }
        binding.crudBtModify.setOnClickListener { updateProduct() }
        binding.crudIvSearch.setOnClickListener { searchID() }
        //binding.crudBtAdd.setOnClickListener { addProduct() }
        //binding.crudBtRemove.setOnClickListener { deleteProduct() }
    }


    private fun backActivity() {
        val back = Intent(this, AdminActivity::class.java)
        startActivity(back)
        finish()
    }

    fun searchID(){
            val productId = binding.crudEdIdProduct.text.toString()
            val product = ProductBD.findProductById(productId)

            if (product != null) {
                binding.crudEtIdProveedor.setText(product.barcode)
                binding.crudEtNameProduct.setText(product.name)
                binding.crudEtPriceProduct.setText(product.price.toString())
                binding.crudEtQuantityProduct.setText(product.quantity.toString())
                val editTextProductId: EditText = binding.crudEtIdProveedor
                editTextProductId.isEnabled = false
            } else {
                Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
            }
        }

    fun updateProduct() {
        val productId = binding.crudEdIdProduct.text.toString()
        val productName = binding.crudEtNameProduct.text.toString()
        val productPrice = binding.crudEtPriceProduct.text.toString().toInt()
        val productQuantity = binding.crudEtQuantityProduct.text.toString().toInt()

        val updatedProduct = ProductData(
            productId,
            productName,
            productPrice,
            productQuantity
        )
        ProductBD.updateProduct(updatedProduct)

        Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
        clear()
    }

     fun clear() {
        binding.crudEtIdProveedor.text.clear()
        binding.crudEtNameProduct.text.clear()
        binding.crudEtPriceProduct.text.clear()
        binding.crudEtQuantityProduct.text.clear()
    }
}
