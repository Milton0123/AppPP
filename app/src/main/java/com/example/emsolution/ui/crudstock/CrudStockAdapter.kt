package com.example.emsolution.ui.crudstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emsolution.R
import com.example.emsolution.back.ProductData
import com.example.emsolution.databinding.ItemCrudStockBinding
import com.squareup.picasso.Picasso

class CrudStockAdapter(private val productList : List<ProductData>) : RecyclerView.Adapter<CrudStockHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrudStockHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crud_stock, parent, false)
        return CrudStockHolder(view)
    }

    override fun onBindViewHolder(holder: CrudStockHolder, position: Int) {
        holder.render(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
class CrudStockHolder(view : View) : RecyclerView.ViewHolder(view){
    private val binding = ItemCrudStockBinding.bind(view)
    fun render(value : ProductData){
        Picasso.get().load(value.image).into(binding.crStockIvImage)
        binding.crudStockTvTitle.text = value.title
        binding.crudStockTvDescription.text = value.description
        binding.crudStockTvAmount.text = value.amount.toString()
        binding.crudStockTvPrice.text = value.price.toString()
    }
}