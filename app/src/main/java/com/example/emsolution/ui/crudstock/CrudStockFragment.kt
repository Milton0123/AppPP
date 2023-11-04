package com.example.emsolution.ui.crudstock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.emsolution.databinding.FragmentCrudStockBinding
import com.example.emsolution.ui.EditProductActivity
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
    private fun initViewModel(){
        viewModel = CrudStockViewModelFactory().create(CrudStockViewModel::class.java)
    }
    private fun onClicks(){
        binding.crudStockBtPlus.setOnClickListener {
            startActivity(Intent(activity, EditProductActivity::class.java))
        }
    }
    private fun showProducts(){
        viewModel.getProduct(
            onSuccess = { productList ->
                val adapter = CrudStockAdapter(productList)
                binding.crudStockRv.adapter = adapter
            }
        ) { e ->
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
