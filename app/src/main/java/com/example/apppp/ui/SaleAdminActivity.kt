package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivitySaleAdminBinding

class SaleAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaleAdminBinding
    private var viewModel = ProductViewModel()
    private var repository = ProductRepository()
    var productBarcode = ""
    private val buyList = mutableListOf<ProductData>()
    private var nameAfter = ""
    private var barcodeAfter = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        onClicks()
        observe()
    }

    private fun initViewModel() {
        val viewModelFactory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[(ProductViewModel::class.java)]
        initRecyclerView(viewModel.getListProducts())
    }

    private fun initRecyclerView(list: MutableList<ProductData>) {
        val adapter = ProductAdapterList(list)
        binding.saleAdminRecyclerViewList.adapter = adapter
    }

    private fun onClicks() {
        binding.saleAdminBtnBack.setOnClickListener { backAdmin() }
        binding.saleAdminImgFind.setOnClickListener { viewModel.validateSearch(binding.saleAdminTextIdProduct.text.toString()) }
        binding.saleAdminBtnLoad.setOnClickListener {
            if (binding.saleAdminTextAmount.text.isNullOrEmpty()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.checkField(
                    binding.saleAdminTextAmount.text.toString().toInt(), productBarcode
                )
            }

        }
        binding.saleAdminBtnSell.setOnClickListener {
            deleteProduct()
            cleanListBD()
        }
    }

    private fun backAdmin() {
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observe() {
        viewModel.barcodeData.observe(this) { barcode ->
            if (barcode.toString() != "product error") {
                binding.saleAdminTextProduct.text = barcode.toString()
                binding.saleAdminTextAmount.isEnabled = true
                binding.saleAdminBtnLoad.isEnabled = true
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
            binding.saleAdminBtnSell.isEnabled = true
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
        binding.saleAdminTextTotal.text = priceTotal.toString()
    }

    private fun cleanListBD() {
        binding.saleAdminTextIdProduct.text.clear()
        binding.saleAdminTextAmount.text.clear()
        binding.saleAdminTextTotal.text = "Total"
        binding.saleAdminTextProduct.text = "Producto"
        nameAfter = ""
        barcodeAfter = ""
        buyList.clear()
        initRecyclerView(ProductBD.list)
    }


    /*
    private fun saleProductList(name: String,amount : Int){
        ProductBD.list.forEach{
            if(it.name == name){
                ProductBD.list.add()
            }
        }
    }
    */
}
