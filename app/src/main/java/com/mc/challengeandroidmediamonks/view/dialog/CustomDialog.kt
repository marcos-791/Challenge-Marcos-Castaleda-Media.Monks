package com.mc.challengeandroidmediamonks.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.model.Photo
import com.squareup.picasso.Picasso

class CustomDialog(context: Context, photo : Photo) : Dialog(context) {
    val photo = photo
    val cntxt = context

    fun showDialog(){
        val dialog = Dialog(cntxt!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_window)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dlgAlbumID = dialog.findViewById<TextView>(R.id.dlgAlbumID)
        val dlgID = dialog.findViewById<TextView>(R.id.dlgID)
        val dlgTitle = dialog.findViewById<TextView>(R.id.dlgTitle)
        val dlgImageUrl = dialog.findViewById<ImageView>(R.id.dlgImageUrl)
        val dlgButton = dialog.findViewById<Button>(R.id.dlgButton)

        dlgAlbumID.text = photo.idAlbum
        dlgID.text = photo.id
        dlgTitle.text = (photo.title)
        Picasso.get().load(photo.urlPhoto).into(dlgImageUrl)
        dlgButton.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

}