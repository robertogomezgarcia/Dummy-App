package com.example.dummyapp.ui.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ItemProductBinding
import com.example.dummyapp.domain.model.product.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private var productlist: List<Product> = emptyList()
) : RecyclerView.Adapter<ProductViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Product>?) {
        list.let {
            if (it != null) {
                productlist = it
            }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false),
            parent as View
        )
    }

    override fun getItemCount() = productlist.take(50).size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.render(productlist[position])
    }
}

class ProductViewHolder(view: View, viewParent: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    private val parent = viewParent

    @SuppressLint("SetTextI18n")
    fun render(product: Product?) {
        binding.tvTitulo.text = product?.title
        binding.tvPrecio.text = product?.price.toString() + ",00 €"
        binding.tvDescripcion.text = product?.description
        binding.tvStock.text = "Stock: "+ product?.stock.toString()
        Picasso.get().load("${product?.images?.first()}").into(binding.ivfotoproducto)

    }


}
