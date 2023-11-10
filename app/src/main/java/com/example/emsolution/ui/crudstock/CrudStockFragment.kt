package com.example.emsolution.ui.crudstock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.emsolution.back.ProductData
import com.example.emsolution.databinding.FragmentCrudStockBinding
import com.example.emsolution.ui.edit.EditProductActivity
import com.google.firebase.FirebaseApp

class CrudStockFragment : Fragment() {

    private var _binding: FragmentCrudStockBinding? = null
    private lateinit var viewModel: CrudStockViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FirebaseApp.initializeApp(requireContext())
        _binding = FragmentCrudStockBinding.inflate(inflater, container, false)
        initViewModel()
        onClicks()
        showProducts()

        val root: View = binding.root
        return root
    }

    private fun initViewModel() {
        viewModel = CrudStockViewModelFactory().create(CrudStockViewModel::class.java)
    }

    private fun onClicks() {
        binding.crudStockBtPlus.setOnClickListener {
            val intent = Intent(activity, EditProductActivity::class.java)
            intent.putExtra("selectFragment", "addOption")
            startActivity(intent)
        }
    }

    private fun showProducts() {
        viewModel.getProduct(
            onSuccess = { productList ->
                val adapter = CrudStockAdapter(
                    productList,
                    itemUpdate = { goToUpdate(it) },
                    itemTrash = { deleteProduct(it) })
                binding.crudStockRv.adapter = adapter
            }
        ) { e ->
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun goToUpdate(item: ProductData) {
        val intent = Intent(activity, EditProductActivity()::class.java)
        intent.putExtra("selectFragment", "updateOption")
        ItemUpdate.myItemUpdate = item
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteProduct(itemTrashBarcode: Int) {
        viewModel.removeProduct(
            itemTrashBarcode,
            onSuccess = {
                showProducts()
            },
            onFailure = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        )
    }

}

object ItemUpdate {
    var myItemUpdate: ProductData? = null
}

