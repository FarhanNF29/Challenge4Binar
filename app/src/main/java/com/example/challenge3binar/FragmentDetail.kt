package com.example.challenge3binar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.challenge3binar.databinding.FragmentDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentDetailBinding
    lateinit var detailView: MyViewModel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickLocation()

        val bundle = Bundle()
        val food = arguments?.getParcelable<DataMenu>("DataMenu")
        val dataFood = food as DataMenu
//        withViewModel()
        val detailViewModel = ViewModelProvider(this).get(MyViewModel::class.java)



        val observer = Observer<Int> { newValue ->
            binding.jumlahAngka.text = newValue.toString()
        }

        viewModel.counter.observe(viewLifecycleOwner, observer)

        binding.ivAdd.setOnClickListener {
            wIncrementCount()
        }

        binding.ivMinus.setOnClickListener {
            wDecrementCount()
        }

        binding.btnTambahKeranjang.setOnClickListener {
            if (binding.jumlahAngka.text.toString().toInt() > 0) {
                val numericPart = food?.hargaMenu?.replace("[^0-9]".toRegex(), "")
                if (numericPart != null) {
                    DatabaseCart.getInstance(requireContext()).simpleChartDao.insert(
                        DataCart(
                            0,
                            dataFood.nameMenu,
                            dataFood.img,
                            numericPart.toInt(),
                            binding.jumlahAngka.text.toString().toInt()
                        )
                    )
                }
                // Tampilkan pesan "Berhasil Menambahkan Ke Keranjang"
                Toast.makeText(requireContext(), "Berhasil Menambahkan Ke Keranjang", Toast.LENGTH_SHORT).show()
                //findNavController().navigate(R.id.action_fragmentDetail_to_fragmentKeranjang)
            }
        }

//        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bot_nav)
//        bottomNav.visibility = View.GONE


        if(food != null){
            val gambarMenu:ImageView = binding.ivMenuDetail
            val namaMenu:TextView = binding.tvNamaMenuDetail
            val hargaMenu:TextView = binding.tvHargaMenuDetail
            val deskripsi:TextView = binding.tvDeskripsiMenu
            val lokasi:TextView = binding.tvLokasiDetail

            gambarMenu.setImageResource(food.img)
            namaMenu.text = food.nameMenu
            hargaMenu.text = food.hargaMenu
            deskripsi.text = food.deskripsi
            lokasi.text = food.lokasi
        }

        detailViewModel.counter.observe(viewLifecycleOwner){
            if (food?.hargaMenu != null){
                val numericPart = food?.hargaMenu.replace("[^0-9]".toRegex(), "")
                binding.btnTambahKeranjang.text = "Tambah Ke Keranjang - Rp. ${it*numericPart.toInt()}"
            }
        }
//        Log.e("IS_GRID", binding.jumlahAngka.text.toString())
//        binding.btnTambahKeranjang.setOnClickListener {
//            DatabaseCart.getInstance(requireContext()).simpleChartDao.insert(DataCart(0, dataFood.nameMenu, dataFood.img, dataFood.hargaMenu.replace("[^0-9]".toRegex(), "", ).toInt(), binding.jumlahAngka.text.toString().toInt() ))
//        }

//        if(binding.jumlahAngka.text.toString().toInt() > 0){
//            binding.btnTambahKeranjang.setOnClickListener {
//                DatabaseCart.getInstance(requireContext()).simpleChartDao.insert(DataCart(0, dataFood.nameMenu, dataFood.img, dataFood.hargaMenu.replace("[^0-9]".toRegex(), "", ).toInt(), binding.jumlahAngka.text.toString().toInt() ))
//            }
//        }


    }


    private fun setOnClickLocation(){
        view?.findViewById<TextView>(R.id.tv_lokasiDetail)?.setOnClickListener{
            navigateToMaps()
        }
    }

    private fun navigateToMaps(){
        val direct = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://maps.app.goo.gl/h4wQKqaBuXzftGK77")
        )
        startActivity(direct)
    }

//    private fun withViewModel(){
//        binding.ivAdd.setOnClickListener{
//            wIncrementCount()
//        }
//        binding.ivMinus.setOnClickListener{
//            wDecrementCount()
//        }
//
//        viewModel.vCounter.observe(viewLifecycleOwner){ result ->
//            binding.jumlahAngka.text = result.toString()
//        }
//    }

    private fun wIncrementCount(){
        viewModel.incrementCount()
    }

    private fun wDecrementCount(){
        viewModel.decrementCount()
    }

    companion object {

    }
}