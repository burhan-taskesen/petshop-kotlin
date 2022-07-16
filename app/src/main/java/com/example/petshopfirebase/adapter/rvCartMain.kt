package com.example.petshopfirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petshopfirebase.util.MyResources
import com.example.petshopfirebase.databinding.CartItemBinding
import com.example.petshopfirebase.ui.cart.CartFragment
import com.example.petshopfirebase.dataClass.CartItem

class rvCartMain(var list : ArrayList<CartItem>, var fragment: CartFragment) : RecyclerView.Adapter<rvCartMain.ViewHolder>() {
    class ViewHolder(var binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCartItemName.text = list.get(position).item.name
        holder.binding.etCartItemPiece.setText(list.get(position).piece.toString())

        holder.binding.btnCartItemUpArrow.setOnClickListener(){
            var tmp = Integer.parseInt(holder.binding.etCartItemPiece.text.toString())
            holder.binding.etCartItemPiece.setText((tmp+1).toString())
        }
        holder.binding.btnCartItemDownArrow.setOnClickListener(){
            var tmp = Integer.parseInt(holder.binding.etCartItemPiece.text.toString())
            if(!(tmp <= 1)) {
                holder.binding.etCartItemPiece.setText((tmp - 1).toString())
            }
        }

        holder.binding.ivCartItemClear.setOnClickListener(){
            MyResources.getInstance().cartItems.remove(list.get(position))
            fragment.setRecycler()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}