package com.example.emsolution.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emsolution.databinding.FragmentSellBinding
import com.example.emsolution.ui.login.LoginActivity

class LogoutFragment : Fragment() {

    private var _binding: FragmentSellBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        val root: View = binding.root

        startActivity(Intent(requireActivity(), LoginActivity::class.java))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
