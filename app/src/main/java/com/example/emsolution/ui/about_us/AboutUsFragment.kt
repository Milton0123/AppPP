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
        val slideshowViewModel =
            ViewModelProvider(this).get(AboutUsViewModel::class.java)
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val carousel: ImageCarousel = binding.carouselAboutus

        val list = mutableListOf<CarouselItem>()
        list.add(CarouselItem("https://www.google.com/url?sa=i&url=https%3A%2F%2Fdinosenglish.edu.vn%2Fplantillas-para-poner-caras-de-dos-1690570925197066%2F&psig=AOvVaw33oPyc6FbEcdoLVVlWFxMB&ust=1699669092321000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLDcq_auuIIDFQAAAAAdAAAAABAJ", "Nuestro Equipo"))
        list.add(CarouselItem("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.es%2Ffotos-vectores-gratis%2Fhombre-formal&psig=AOvVaw1n6PlpAYNMRI76hgdNB4lj&ust=1699669170371000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIjF05uvuIIDFQAAAAAdAAAAABAE", "Elizalde Eduardo Agustin"))
        list.add(CarouselItem("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.es%2Ffotos-vectores-gratis%2Fhombre&psig=AOvVaw1n6PlpAYNMRI76hgdNB4lj&ust=1699669170371000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIjF05uvuIIDFQAAAAAdAAAAABAY", "Maletta Milton Ezequiel"))
        carousel.addData(list)
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
