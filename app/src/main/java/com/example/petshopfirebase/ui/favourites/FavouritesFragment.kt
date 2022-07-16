package com.example.petshopfirebase.ui.favourites

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petshopfirebase.R
import com.example.petshopfirebase.adapter.rvFavourites
import com.example.petshopfirebase.util.MyResources
import com.example.petshopfirebase.databinding.FragmentFavouritesBinding
import com.example.petshopfirebase.ui.home.HomeFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        //requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigate(HomeFragmentDirections)
    }

    fun setRecycle(){
        if(MyResources.getInstance().favItems?.size != 0) {
            binding.rvFavourites.adapter = rvFavourites(MyResources.getInstance().favItems!!, this)
            binding.rvFavourites.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            binding.tvBlank.visibility = View.INVISIBLE
        }
        else {
            binding.rvFavourites.layoutManager = null
            binding.tvBlank.visibility = View.VISIBLE
        }
    }

    fun isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    } //Tema kontrol edici

    fun fabFunc(view : View){

    }
}