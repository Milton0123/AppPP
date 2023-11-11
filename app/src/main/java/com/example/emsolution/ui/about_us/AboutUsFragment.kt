package com.example.emsolution.ui.about_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.emsolution.databinding.FragmentAboutUsBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val carousel: ImageCarousel = binding.carouselAboutus

        val list = mutableListOf<CarouselItem>()
        list.add(CarouselItem("https://i.ibb.co/brKMKV6/Whats-App-Image-2023-11-10-at-22-32-51.jpg", "Nuestro Logo"))
        list.add(CarouselItem("https://i.ibb.co/HK63hFK/Whats-App-Image-2023-11-10-at-22-14-19.jpg", "Elizalde Eduardo Agustin"))
        list.add(CarouselItem("https://i.ibb.co/XsZWZLK/Whats-App-Image-2023-11-10-at-22-19-11.jpg", "Maletta Milton Ezequiel"))
        carousel.addData(list)
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}