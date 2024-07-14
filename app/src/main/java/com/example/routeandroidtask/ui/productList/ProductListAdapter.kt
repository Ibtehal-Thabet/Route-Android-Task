package com.example.routeandroidtask.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Product
import com.example.routeandroidtask.R
import com.example.routeandroidtask.databinding.ItemProductBinding

class ProductListAdapter(private var productList: List<Product?>?) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProduct = productList!![position]
        holder.bind(itemProduct)

        var isInFav = false
        onFavClickListener?.let {
            holder.itemBinding.favButton.setOnClickListener {
                onFavClickListener?.onFavClick(position, itemProduct)
                isInFav = !isInFav
                if (isInFav){
                    holder.itemBinding.favButton.setImageResource(R.drawable.in_fav)
                }else{
                    holder.itemBinding.favButton.setImageResource(R.drawable.fav_border)
                }
            }
        }
    }

    override fun getItemCount(): Int = productList?.size ?: 0

    fun bindProducts(products: List<Product?>) {
        productList = products
        notifyDataSetChanged()
    }

    var onFavClickListener: OnFavClickListener? = null

    fun interface OnFavClickListener {
        fun onFavClick(position: Int, product: Product?)
    }

    class ViewHolder(val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product?) {
            itemBinding.product = product
            itemBinding.invalidateAll()
        }
    }
}