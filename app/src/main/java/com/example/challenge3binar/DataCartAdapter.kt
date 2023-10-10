package com.example.challenge3binar

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataCartAdapter(private val context: Context) : RecyclerView.Adapter<DataCartAdapter.DataCartViewHolder>() {
    private var dataCartList: List<DataCart> = emptyList()

    inner class DataCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.tv_namaMenu)
        val itemImageView: ImageView = itemView.findViewById(R.id.iv_gambarMenu)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.tv_hargaMenu)
        val itemQuantityTextView: TextView = itemView.findViewById(R.id.tv_jumlahMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataCartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_keranjang, parent, false)
        return DataCartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataCartViewHolder, position: Int) {
        val currentDataCart = dataCartList[position]

        holder.itemNameTextView.text = currentDataCart.itemName
        holder.itemImageView.setImageResource(currentDataCart.itemImage ?: R.drawable.ayam_rica)
        holder.itemPriceTextView.text = "Rp. ${currentDataCart.itemPrice}"
        holder.itemQuantityTextView.text = "${currentDataCart.itemQuantity}"
    }

    override fun getItemCount(): Int {
        return dataCartList.size
    }

    fun setDataCartList(dataCartList: List<DataCart>) {
        this.dataCartList = dataCartList
        notifyDataSetChanged()
    }
}
