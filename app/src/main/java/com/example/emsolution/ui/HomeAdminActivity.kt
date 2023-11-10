package com.example.emsolution.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.emsolution.R
import com.example.emsolution.back.ProductData
import com.example.emsolution.databinding.ActivityHomeAdminBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class HomeAdminActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeAdminBinding
    val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val carousel: ImageCarousel = findViewById(R.id.carousel)
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

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_news,
                R.id.nav_sell,
                R.id.nav_see_stock,
                R.id.nav_crud_stock_fr,
                R.id.nav_about_us,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val hola = 0
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}