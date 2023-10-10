package com.example.challenge3binar

import androidx.navigation.fragment.findNavController
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentKeranjang.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentKeranjang : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var dataCartAdapter: DataCartAdapter
    private lateinit var dataCartDao: CartDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keranjang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById((R.id.recyclerView))
        recyclerView.layoutManager = LinearLayoutManager(context)
        dataCartDao = DatabaseCart.getInstance(requireContext()).simpleChartDao

        dataCartAdapter = DataCartAdapter(requireContext(), dataCartDao)
        recyclerView.adapter = dataCartAdapter

        // Inisialisasi database
        val database = DatabaseCart.getInstance(requireContext())
        val dataCartDao = database.simpleChartDao

        // Mengamati perubahan data dari database dan memperbarui adapter
        dataCartDao.getAllItem().observe(viewLifecycleOwner, Observer { dataCartList ->
            dataCartAdapter.setDataCartList(dataCartList)

            // Hitung total harga dari dataCartList
            var totalHarga = 0
            for (item in dataCartList) {
                val itemTotalPrice = item.itemPrice?.times(item.itemQuantity) ?: 0
                totalHarga += itemTotalPrice
            }

            // Tampilkan total harga di TextView
            val totalHargaTextView: TextView = view.findViewById(R.id.tv_totalHargaCart)
            totalHargaTextView.text = "Rp. ${totalHarga}"
        })
    }

}