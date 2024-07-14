package com.example.routeandroidtask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Product
import com.example.routeandroidtask.databinding.ItemSearchBinding

class SearchAdapter(private var searchedProductList: List<Product?>?) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProduct = searchedProductList!![position]
        holder.bind(itemProduct)
    }

    override fun getItemCount(): Int = searchedProductList?.size ?: 0

    fun bindProducts(products: List<Product?>) {
        searchedProductList = products
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product?) {
            itemBinding.product = product
            itemBinding.invalidateAll()
        }
    }
}