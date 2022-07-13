package com.example.petshopfirebase

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.petshopfirebase.core.MyResources
import com.example.petshopfirebase.dataAccess.AppDatabase
import com.example.petshopfirebase.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    public lateinit var binding: ActivityMainBinding
    public lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyResources.getInstance().dataBaseFavourites = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "favourites").build()
        MyResources.getInstance().itemDao = MyResources.getInstance().dataBaseFavourites.itemDao()
        MyResources.getInstance().activity = this

        CoroutineScope(Dispatchers.IO).launch {
            MyResources.getInstance().favItems = ArrayList(MyResources.getInstance().itemDao.getAll())
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        var appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_favourites, R.id.navigation_cart, R.id.navigation_profile)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Navigation.findNavController(binding.fragmentContainerView2).navigate()
        Log.d("Debug mesaj","actv on create")

    }

    override fun onResume() {
        super.onResume()
        Log.d("theme debug", isDarkThemeOn().toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        //saveDatas()
    }

    fun isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    } //Tema kontrol edici

    fun saveDatas(){
        MyResources.getInstance().favItems?.forEach {
            MyResources.getInstance().itemDao.insertAll(it)
        }
    }
}