package com.example.petshopfirebase.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petshopfirebase.MainActivity
import com.example.petshopfirebase.R
import com.example.petshopfirebase.core.MyResources
import com.example.petshopfirebase.databinding.AlertAddItemBinding
import com.example.petshopfirebase.databinding.BotItemBinding
import com.example.petshopfirebase.entities.BotItem
import com.example.petshopfirebase.ui.favourites.FavouritesFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class rvFavourites(var list : ArrayList<BotItem>, var fragment: FavouritesFragment) : RecyclerView.Adapter<rvFavourites.ViewHolder>() {

    private var lightModeTextColor : String = "#222224"

    class ViewHolder(var binding: BotItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvFavourites.ViewHolder {
        return ViewHolder(BotItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list.get(position).imageSource).into(holder.binding.IVItem)
        holder.binding.TVName.text = list.get(position).name
        holder.binding.TVPrice.text = list.get(position).price.toString()

        /** ürünün favorilerde olup olmadığını kontrol et **/
        if(MyResources.getInstance().favItems!!.contains(list.get(position))){
            holder.binding.IVLike.setImageResource(R.drawable.like)
            holder.binding.IVLike.contentDescription = "true"
        } //is item liked ?

        if(!MyResources.getInstance().isDarkTheme){
            holder.binding.clBotItem.setBackgroundColor(Color.WHITE)
            holder.binding.TVPrice.setTextColor(Color.parseColor(lightModeTextColor))
            holder.binding.TVName.setTextColor(Color.parseColor(lightModeTextColor))
        } // theme check

        holder.binding.IVLike.setOnClickListener {
            if(it.contentDescription.equals("false")) {
                /** Kullanıcı ürünü favorilerine ekledi */
                (it as ImageView).setImageResource(R.drawable.like) //Kalp görselini dolu olanla değiştir
                it.contentDescription = "true"  //Açıklamayı true olarak değiştir
                MyResources.getInstance().favItems?.add(list.get(position))
            }
            else {
                /** Kullanıcı ürünü favorilerinden çıkardı */
                (it as ImageView).setImageResource(R.drawable.like_blank) //Kalp görselini boş olanla değiştir
                it.contentDescription = "false" //Açıklamayı false olarak değiştir
                CoroutineScope(Dispatchers.IO).launch {
                    MyResources.getInstance().itemDao.delete(list.get(position))

                    MyResources.getInstance().favItems?.remove(list.get(position))

                    withContext(Dispatchers.Main){
                        fragment.setRecycle()
                    }
                }
            }
        } //like button things

        holder.binding.IVItem.setOnClickListener {
            MyResources.getInstance().activity.navController
        }

        holder.binding.IVItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                var myAlert = AlertDialog.Builder(holder.itemView.context)
                var binding = AlertAddItemBinding.inflate(LayoutInflater.from(holder.itemView.context))
                binding.btnUp.setOnClickListener(){
                    var tmp = Integer.parseInt(binding.etPiece.text.toString())
                    binding.etPiece.setText((tmp+1).toString())
                }
                binding.btnDown.setOnClickListener(){
                    var tmp = Integer.parseInt(binding.etPiece.text.toString())
                    binding.etPiece.setText((tmp-1).toString())
                }
                myAlert.setView(binding.root)
                myAlert.setPositiveButton("Ekle",object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //TODO("Ekleme işlemi yapılacak")
                        Toast.makeText(holder.itemView.context,"Ürün sepete eklendi.",0).show()
                    }
                })
                myAlert.setNegativeButton("İptal",null)
                myAlert.show()
                return true
            }
        })
    }
}