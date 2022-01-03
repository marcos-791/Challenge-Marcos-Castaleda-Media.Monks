package com.mc.challengeandroidmediamonks.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.adapter.AlbumViewAdapter
import com.mc.challengeandroidmediamonks.viewmodel.AlbumViewModel

class AlbumListFragment : Fragment() {
    private lateinit var recyclerAdapter : AlbumViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_album_list, container, false)

        initViewModel(view)
        initModel()

        return view
    }

    private fun initViewModel(view : View) {
        val  recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = AlbumViewAdapter(context)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initModel(){
        val viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, {
            if(it != null){
                recyclerAdapter.setUpdateData(it)
            }else{
                Toast.makeText(activity,getString(R.string.error_getting_data),Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            AlbumListFragment()
    }
}