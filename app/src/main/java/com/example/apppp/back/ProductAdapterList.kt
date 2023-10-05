package com.example.apppp.back

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppp.R
import com.example.apppp.databinding.ItemRvListBinding

class ProductAdapterList(private val productList : List<ProductData>): RecyclerView.Adapter<ProductListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_list,parent,false)
        return ProductListHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListHolder, position: Int) {
        holder.render(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
class ProductListHolder(view : View):RecyclerView.ViewHolder(view){
    private val binding = ItemRvListBinding.bind(view)
    fun render(value : ProductData){
        binding.listBarcode.text = value.barcode
        binding.listName.text = value.name
        binding.listPrice.text = value.price.toString()
        binding.listQuantity.text = value.quantity.toString()
    }
}