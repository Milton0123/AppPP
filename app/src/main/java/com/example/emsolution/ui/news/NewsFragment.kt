package com.example.emsolution.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emsolution.databinding.FragmentNewsBinding
import com.google.firebase.firestore.FirebaseFirestore
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val list = mutableListOf<CarouselItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        showCarousel()
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showCarousel(){

        val carousel: ImageCarousel = binding.carousel
        val db = FirebaseFirestore.getInstance()
        val nameCollecttion = "productosTextil"

        val qCollection = db.collection(nameCollecttion)

        qCollection.get().addOnSuccessListener { productosQuerySnapshot ->
            for (productoDocument in productosQuerySnapshot.documents) {
                val productImage = productoDocument.getString("imagen")
                val imageUrlString = productImage.toString()
                list.add(CarouselItem(imageUrlString))
            }

            carousel.addData(list)
        }
    }

}
