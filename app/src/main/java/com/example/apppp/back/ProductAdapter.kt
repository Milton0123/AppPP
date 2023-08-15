package com.example.apppp.back

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppp.R
import com.example.apppp.databinding.ItemRvSalesBinding

class ProductAdapter(private val productList: List<ProductData>) :
    RecyclerView.Adapter<ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sales, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.render(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}

class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRvSalesBinding.bind(view)
    fun render(value: ProductData) {
        binding.itemTvId.text = value.barcode
        binding.itemTvName.text = value.name
        binding.itemTvQuantity.text = value.quantity.toString()
        binding.itemTvPrice.text = value.price.toString()
    }
}
