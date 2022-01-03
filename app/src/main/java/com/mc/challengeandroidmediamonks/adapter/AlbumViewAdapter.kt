package com.mc.challengeandroidmediamonks.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.constants.Constants
import com.mc.challengeandroidmediamonks.databinding.ItemAlbumBinding
import com.mc.challengeandroidmediamonks.model.Album
import com.mc.challengeandroidmediamonks.view.MainActivity

class AlbumViewAdapter(private val context: Context?) : RecyclerView.Adapter<AlbumViewAdapter.MyViewHolder>(){
    private var items = ArrayList<Album>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdateData(items : ArrayList<Album>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = ItemAlbumBinding.bind(view)

        //completo los items a llenar
        fun bind(data : Album){
            binding.idAlbum.text = data.id
            binding.titleAlbum.text = data.title
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

        holder.binding.mainLayout.setOnClickListener{  showMessage(items[position].id) }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album,parent,false)

        return MyViewHolder(view)
    }

    //Tamaño del Adapter
    override fun getItemCount(): Int = items.size

    private fun showMessage(Id : String){
        //variables globales usadas para SharedPreferences
        val constants = Constants()
        //Inicio SharedPreferences para guardar variables
        val sharedPreferences : SharedPreferences =
            context!!.getSharedPreferences(constants.database, Context.MODE_PRIVATE)

        //Inicio el editor
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        //inserto el id clickeado por el usuario
        editor.putString(constants.idAlbum,Id)
        //guardo el valor
        editor.apply()

        //Inicio el Fragment PhotoFragment que mostrará la lista con el Id que acabo de guardar
        val mainActivity : MainActivity = context as MainActivity
        mainActivity.changeTab()
    }

}