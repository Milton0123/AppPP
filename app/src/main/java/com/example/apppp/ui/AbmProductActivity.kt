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

    private fun initViewModel() {
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }

    private fun initRecyclerView(list: MutableList<ProductData>) {
        val adapter = ProductAdapter(list)
        binding.crudRvProduct.adapter = adapter
    }

    private fun onClicks() {
        binding.crudProductIvBack.setOnClickListener { backActivity() }
        binding.crudBtModify.setOnClickListener {
            updateProduct()
            it.isEnabled = false
        }
        binding.crudIvSearch.setOnClickListener {
            searchID()
        }
        binding.crudBtAdd.setOnClickListener { addProduct() }
        binding.crudBtRemove.setOnClickListener { deleteProduct() }
    }


    private fun backActivity() {
        val back = Intent(this, AdminActivity::class.java)
        startActivity(back)
        finish()
    }

    fun searchID() {
        val productId = binding.crudEdIdProduct.text.toString()
        val product = viewModel.findProductById(productId)

        if (product != null) {
            binding.crudEtIdBarcode.setText(product.barcode)
            binding.crudEtNameProduct.setText(product.name)
            binding.crudEtPriceProduct.setText(product.price.toString())
            binding.crudEtQuantityProduct.setText(product.quantity.toString())
            val editTextProductId: EditText = binding.crudEtIdBarcode
            binding.crudBtModify.isEnabled = true
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
        viewModel.updateProduct(updatedProduct)
        initRecyclerView(ProductBD.list)

        Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
        clear()
    }

    fun clear() {
        binding.crudEtIdBarcode.text.clear()
        binding.crudEtNameProduct.text.clear()
        binding.crudEtPriceProduct.text.clear()
        binding.crudEtQuantityProduct.text.clear()
    }

    private fun addProduct() {
        val productId = binding.crudEtIdBarcode.text.toString()
        val productName = binding.crudEtNameProduct.text.toString()
        val productPrice = binding.crudEtPriceProduct.text.toString()
        val productQuantity = binding.crudEtQuantityProduct.text.toString()

        if (((productId.isEmpty()) || (productName.isEmpty()) || (productPrice.isEmpty()) || (productQuantity.isEmpty()))) {
            Toast.makeText(this, "hay campos vacios", Toast.LENGTH_SHORT).show()
        } else {
            val productDataActivity =
                ProductData(productId, productName, productPrice.toInt(), productQuantity.toInt())
            ProductBD.list.add(productDataActivity)
            initRecyclerView(ProductBD.list)
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
            clear()
        }
    }

    private fun deleteProduct() {
        val productId = binding.crudEtIdBarcode.text.toString()

        if (productId.toIntOrNull() != null) {
            delete(productId)
        } else {
            Toast.makeText(this, "ID de producto inválido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun delete(id: String) {

        val productDelete = ProductBD.list.find { it.barcode == id }

        if (productDelete != null) {
            ProductBD.list.remove(productDelete)
            Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show()
            initRecyclerView(ProductBD.list)
            println("Contenido de la lista después de eliminar el producto:")
            for (product in ProductBD.list)
                println(product)

            clearId()
        } else {
            Toast.makeText(this, "No se ha encontrado el producto, ID invalido", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun clearId() {
        binding.crudEtIdBarcode.text.clear()
    }

}
