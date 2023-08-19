package com.example.apppp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.apppp.back.*
import com.example.apppp.databinding.ActivitySalesBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

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
        val adapter = ProductAdapterList(list)
        binding.saleEmployeeRecyclerViewList.adapter = adapter
    }

    private fun onClicks() {
        binding.saleEmployeeBtnBack.setOnClickListener { backEmployee() }
        binding.saleEmployeeImgFind.setOnClickListener { viewModel.validateSearch(binding.saleEmployeeTextIdProduct.text.toString()) }
        binding.saleEmployeeBtnLoad.setOnClickListener {
            if (binding.saleEmployeeTextAmount.text.isNullOrEmpty()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.checkField(
                    binding.saleEmployeeTextAmount.text.toString().toInt(), productBarcode
                )
            }
        }
        binding.saleEmployeeBtnSell.setOnClickListener {
            deleteProduct()
            cleanListBD()
        }
        binding.saleEmployeeBtnQr.setOnClickListener {
            startBarcodeScanner()
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
                binding.saleEmployeeTextProduct.text = barcode.toString()
                binding.saleEmployeeTextAmount.isEnabled = true
                binding.saleEmployeeBtnLoad.isEnabled = true
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
            binding.saleEmployeeBtnSell.isEnabled = true
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
        binding.saleEmployeeTextTotal.text = priceTotal.toString()
    }

    private fun cleanListBD() {
        binding.saleEmployeeTextIdProduct.text.clear()
        binding.saleEmployeeTextAmount.text.clear()
        binding.saleEmployeeTextTotal.text = "Total"
        binding.saleEmployeeTextProduct.text = "Producto"
        nameAfter = ""
        barcodeAfter = ""
        buyList.clear()
        initRecyclerView(ProductBD.list)
    }


    private fun startBarcodeScanner() {
        val integrator = IntentIntegrator(this)  // Crea un objeto IntentIntegrator

        // Configura opciones para el escaneo
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Escanea un código de barras")
        integrator.setCameraId(0) // Usar cámara trasera
        integrator.setBeepEnabled(false)

        // Inicia el escaneo
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents != null) {
            val scannedBarcodeValue = result.contents
            val editableScannedValue =
                Editable.Factory.getInstance().newEditable(scannedBarcodeValue)
            binding.saleEmployeeTextIdProduct.text = editableScannedValue
        } else {
            // Manejar el caso en que el escaneo no fue exitoso
            Toast.makeText(this, "no se ha escaneado ningun producto", Toast.LENGTH_SHORT).show()
        }
    }
}
