package com.example.petshopfirebase.ui.favourites

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petshopfirebase.adapter.rvFavourites
import com.example.petshopfirebase.core.MyResources
import com.example.petshopfirebase.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavouritesBinding.inflate(inflater)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MyResources.getInstance().isDarkTheme = isDarkThemeOn()
        if(!MyResources.getInstance().isDarkTheme){
            binding.rvFavourites.setBackgroundColor(Color.WHITE)
            binding.rvFavourites.setBackgroundColor(Color.WHITE)
        }

        setRecycle()
    }

    fun setRecycle(){
        if(MyResources.getInstance().favItems?.size != 0) {
            binding.rvFavourites.adapter = rvFavourites(MyResources.getInstance().favItems!!, this)
            binding.rvFavourites.layoutManager =
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }
        else
            binding.rvFavourites.layoutManager = null
    }

    fun isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    } //Tema kontrol edici
}