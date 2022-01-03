package com.mc.challengeandroidmediamonks.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.databinding.ItemPhotoBinding
import com.mc.challengeandroidmediamonks.model.Photo
import com.mc.challengeandroidmediamonks.view.dialog.CustomDialog
import com.squareup.picasso.Picasso

class PhotoViewAdapter(private val context : Context?) : RecyclerView.Adapter<PhotoViewAdapter.MyViewHolder>() {
    private var items = ArrayList<Photo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdateData(items : ArrayList<Photo>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = ItemPhotoBinding.bind(view)

        fun bind(data : Photo){
            binding.idAlbum.text = data.id
            binding.titleAlbum.text = data.title

            Picasso.get().load(data.thumbnailUrl).into(binding.image)
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

        holder.binding.moreData.setOnClickListener {
            val dlg = CustomDialog(context!!,items[position])
            dlg.showDialog()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

}