package com.example.emsolution.ui.edit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.emsolution.databinding.ActivityEditProductBinding
import com.example.emsolution.ui.HomeAdminActivity
import com.example.emsolution.ui.crudstock.ItemUpdate
import com.google.firebase.FirebaseApp

class EditProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProductBinding
    private lateinit var viewModel: EditProductViewModel
    private var imageUriEdit: Uri? = null
    private lateinit var imagePicker: ActivityResultLauncher<Intent>
    private var imageEdit = false
    private var selectImageEdit: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        initViewModel()
        findImage()
        onClick()
        observer()
        selectFragment()
    }

    private fun initViewModel() {
        viewModel = EditProductViewModelFactory().create(EditProductViewModel::class.java)
    }

    private fun observer() {
        viewModel.amountData.observe(this) {
            initValues(it)
        }
    }

    private fun initValues(amount: Int) {
        binding.editProductEtAmount.setText(amount.toString())
    }

    private fun selectFragment() {
        val fragmentOption = intent.getStringExtra("selectFragment")
        if (fragmentOption != null) {
            when (fragmentOption) {
                "updateOption" -> {
                    updateOption()
                }
                "addOption" -> {
                    addOption()
                }
            }
        }
    }

    private fun updateOption() {
        loadFieldUpdate()
        viewModel.findNameDocument(
            binding.editProductEtBarcode.text.toString().toInt(),
            documentEdit = { saveUpdate(it) }
        )

    }

    private fun addOption() {
        binding.editProductBtSave.setOnClickListener {
            viewModel.addOption(
                binding.editProductEtBarcode.text.toString().toInt(),
                imageUriEdit.toString(),
                binding.editProductEtNameProduct.text.toString(),
                binding.editProductEtDescription.text.toString(),
                binding.editProductEtAmount.text.toString().toInt(),
                binding.editProductEtPrice.text.toString().toInt(),
                onSuccess = { s ->
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
                    cleanFields()
                },
            ) { e ->
                Toast.makeText(this, e, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun findImage() {
        // Verifica y solicita permisos de lectura de almacenamiento
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }

        // Inicializa el ActivityResultLauncher
        imagePicker =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    imageUriEdit = data?.data
                    binding.editProductIvImage.setImageURI(imageUriEdit)
                    imageEdit = true
                }
            }
    }

    fun onClick() {
        binding.editProductIvBack.setOnClickListener {
            startActivity(Intent(this, HomeAdminActivity::class.java))
            finish()
        }
        binding.editProductBtEditImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            imagePicker.launch(intent)
        }
        binding.editProductBtArrowUp.setOnClickListener {
            val valueAmount = binding.editProductEtAmount.text.toString()
            val resultAmount = valueAmount.toIntOrNull() ?: 0
            viewModel.addAmount(
                resultAmount,
                amount = "mas"
            )
        }
        binding.editProductBtArrowDown.setOnClickListener {
            val valueAmount = binding.editProductEtAmount.text.toString()
            val resultAmount = valueAmount.toIntOrNull() ?: 0
            viewModel.addAmount(
                resultAmount,
                amount = "menos"
            )
        }
    }

    private fun cleanFields() {
        binding.editProductIvImage.setImageURI(null)
        binding.editProductEtBarcode.text.clear()
        binding.editProductEtNameProduct.text.clear()
        binding.editProductEtDescription.text.clear()
        binding.editProductEtAmount.text.clear()
        binding.editProductEtPrice.text.clear()
    }

    private fun loadFieldUpdate() {
        val updateDate = ItemUpdate.myItemUpdate
        if (updateDate != null) {
            binding.editProductIvImage.setImageURI(updateDate.image)
            binding.editProductEtBarcode.setText(updateDate.barcode.toString())
            binding.editProductEtBarcode.isEnabled = false
            binding.editProductEtNameProduct.setText(updateDate.title)
            binding.editProductEtDescription.setText(updateDate.description)
            binding.editProductEtAmount.setText(updateDate.amount.toString())
            binding.editProductEtPrice.setText(updateDate.price.toString())
        }
    }

    fun saveUpdate(nameDocument: String) {
        binding.editProductBtSave.setOnClickListener { updateFields(nameDocument) }
    }

    private fun updateFields(nameDocument: String) {
        val updateData = ItemUpdate.myItemUpdate

        if (updateData != null) {

            if(!imageEdit){
                selectImageEdit = updateData.image
            }else{
                selectImageEdit = imageUriEdit
            }
            viewModel.updateOption(
                selectImageEdit,
                binding.editProductEtNameProduct.text.toString(),
                binding.editProductEtDescription.text.toString(),
                binding.editProductEtAmount.text.toString().toInt(),
                binding.editProductEtPrice.text.toString().toInt(),
                nameDocument,
                onSuccess = { onSuccess ->
                    Toast.makeText(this, onSuccess, Toast.LENGTH_SHORT).show()
                },
                onError = { onError ->
                    Toast.makeText(this, onError, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}