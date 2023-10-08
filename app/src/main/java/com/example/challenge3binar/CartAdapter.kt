package com.example.challenge3binar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3binar.databinding.ListMenuGridBinding
import com.example.challenge3binar.databinding.ListMenuLinearBinding

class CartAdapter(private val listCart: ArrayList<DataCart>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((DataMenu) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListMenuLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinearMenuHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val linearHolder = holder as LinearMenuHolder

    }

    override fun getItemCount(): Int {
        return listCart.size
    }
}
