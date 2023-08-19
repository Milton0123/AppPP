package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivitySalesBinding

class SalesEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalesBinding
    private var viewModel = ProductViewModel()
    private var repository = ProductRepository()
    var productBarcode = ""
    private val buyList = mutableListOf<ProductData>()
    private var nameAfter = ""
    private var barcodeAfter = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClicks()
        initViewModel()
        observe()
    }

    private fun initViewModel() {
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }

    private fun initRecyclerView(list: MutableList<ProductData>) {
        val adapter = ProductAdapter(list)
        binding.saleRecyclerViewList.adapter = adapter
    }

    private fun onClicks() {
        binding.saleBtnBack.setOnClickListener { backEmployee() }
        binding.saleImgFind.setOnClickListener { viewModel.validateSearch(binding.saleTextIdproducto.text.toString()) }
        binding.saleBtnLoad.setOnClickListener {
            if (binding.saleTextAmount.text.isNullOrEmpty()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.checkField(
                    binding.saleTextAmount.text.toString().toInt(), productBarcode
                )
            }

        }
        binding.saleBtnSell.setOnClickListener {
            deleteProduct()
            cleanListBD()
        }
    }

    private fun backEmployee() {
        val intent = Intent(this, EmployeeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observe() {
        viewModel.barcodeData.observe(this) { barcode ->
            if (barcode.toString() != "product error") {
                binding.saleTextProduct.text = barcode.toString()
                binding.saleTextAmount.isEnabled = true
                binding.saleBtnLoad.isEnabled = true
                productBarcode = barcode.toString()
            }
            Toast.makeText(this, barcode.toString(), Toast.LENGTH_SHORT).show()
        }
        viewModel.fieldDataAmount.observe(this) {
            if (it.barcode == "error") {
                Toast.makeText(
                    this,
                    "supera el stock disponible que es de ${it.quantity} o el campo esta vacio",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addProductList(it.barcode, it.name, it.price, it.quantity)
                totalProduct()
            }
        }
    }

    private fun addProductList(barcode: String, name: String, price: Int, quantity: Int) {
        var quantityLimit = 0
        if (nameAfter == name) {
            ProductBD.list.forEach {
                if (it.name == name) {
                    quantityLimit = it.quantity
                }
            }
            buyList.forEach {
                if (barcodeAfter != barcode) {
                    it.quantity += quantity
                    if (it.quantity > quantityLimit) {
                        it.quantity -= (it.quantity - quantityLimit)
                        Toast.makeText(this, "ya no hay stock", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            initRecyclerView(buyList)
        } else {
            buyList.add(ProductData(barcode, name, price, quantity))
            barcodeAfter = barcode
            nameAfter = name
            binding.saleBtnSell.isEnabled = true
            initRecyclerView(buyList)
        }
    }

    private fun deleteProduct() {
        ProductBD.list.forEach() { listBD ->
            buyList.forEach { listSell ->
                if (listBD.barcode == listSell.barcode) {
                    listBD.quantity -= listSell.quantity
                    Toast.makeText(this, "el producto se elimino con exito", Toast.LENGTH_SHORT)
                        .show()
                    initRecyclerView(ProductBD.list)
                }
            }
        }
    }


    private fun totalProduct() {
        var priceTotal = 0
        buyList.forEach {
            val price = it.price.toString()
            val quantity = it.quantity
            priceTotal += price.toInt() * quantity
        }
        binding.saleTextTotal.text = priceTotal.toString()
    }

    private fun cleanListBD() {
        binding.saleTextIdproducto.text.clear()
        binding.saleTextAmount.text.clear()
        binding.saleTextTotal.text = "Total"
        binding.saleTextProduct.text = "Producto"
        nameAfter = ""
        barcodeAfter = ""
        buyList.clear()
        initRecyclerView(ProductBD.list)
    }
}
