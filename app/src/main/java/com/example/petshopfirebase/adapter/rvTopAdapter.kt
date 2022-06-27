package com.example.petshopfirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petshopfirebase.R
import com.example.petshopfirebase.core.MyResources
import com.example.petshopfirebase.databinding.TopItemBinding
import com.example.petshopfirebase.entities.TopItem
import com.example.petshopfirebase.ui.home.HomeFragment
import java.util.*
import kotlin.collections.ArrayList

class rvTopAdapter(var list : ArrayList<TopItem>, var fragment: HomeFragment) : RecyclerView.Adapter<rvTopAdapter.ViewHolder>() {

    class ViewHolder(var binding:  TopItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvTopAdapter.ViewHolder {
        return ViewHolder(TopItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: rvTopAdapter.ViewHolder, position: Int) {
        Glide.with(holder.binding.ivMain).load(list.get(position).imageSource).into(holder.binding.ivMain)
        if(!MyResources.getInstance().isDarkTheme) {
            holder.binding.ivMask.setImageResource(R.drawable.lightmode)
        } //theme check

        holder.itemView.setOnClickListener(){
            fragment.setBotAdapter(list.get(position).name)
        } //change bot recycle elements
    }

    override fun getItemCount(): Int {
        return list.size
    }
}