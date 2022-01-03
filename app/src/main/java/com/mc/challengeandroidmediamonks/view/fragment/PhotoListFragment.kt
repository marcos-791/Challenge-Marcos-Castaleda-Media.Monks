package com.mc.challengeandroidmediamonks.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.adapter.PhotoViewAdapter
import com.mc.challengeandroidmediamonks.constants.Constants
import com.mc.challengeandroidmediamonks.viewmodel.PhotoViewModel

class PhotoListFragment : Fragment() {
    private lateinit var recyclerAdapter : PhotoViewAdapter
    private var idAlbum : String? = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_photo_list, container, false)

        initViewModel(view)
        init(view)
        initModel()

        return view
    }

    private fun initViewModel(view : View) {
        val  recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = PhotoViewAdapter(context)
        recyclerView.adapter = recyclerAdapter
    }

    private fun init(view : View){
        //variables globales usadas para SharedPreferences
        val constants = Constants()
        //Inicio SharedPreferences para usar la variable guardada
        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences(constants.database, Context.MODE_PRIVATE)
        //variable guardada asignada a "id"
        idAlbum = sharedPreferences.getString(constants.idAlbum,"0")

        val idOfAlbum = view.findViewById<TextView>(R.id.idOfAlbum)
        idOfAlbum.text = idAlbum
    }

    private fun initModel(){
        val viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, {
            if(it != null){
                recyclerAdapter.setUpdateData(it)
            }else{
                Toast.makeText(activity,getString(R.string.error_getting_data), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(idAlbum?.toIntOrNull()!!)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PhotoListFragment()
    }
}