package com.example.emsolution.ui.see_stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.emsolution.databinding.FragmentSeeStockBinding
import com.example.emsolution.ui.crudstock.CrudStockAdapter
import com.example.emsolution.ui.crudstock.CrudStockViewModel
import com.example.emsolution.ui.crudstock.CrudStockViewModelFactory

class SeeStockFragment : Fragment() {

    private var _binding: FragmentSeeStockBinding? = null
    private lateinit var viewModel: SeeStockViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeStockBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViewModel()
        showProducts()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        viewModel = SeeStockViewModelFactory().create(SeeStockViewModel::class.java)
    }

    private fun showProducts() {
        viewModel.getProduct(
            onSuccess = { productList ->
                val adapter = SeeStockAdapter(productList)
                binding.stockAdminRv.adapter = adapter
            }
        ) { e ->
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
