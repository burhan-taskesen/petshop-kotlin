package com.example.petshopfirebase.core

import com.example.petshopfirebase.MainActivity
import com.example.petshopfirebase.dataAccess.AppDatabase
import com.example.petshopfirebase.dataAccess.ItemDao
import com.example.petshopfirebase.entities.BotItem
import com.example.petshopfirebase.entities.TopItem
import com.example.petshopfirebase.util.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyResources() {

    private var firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var favItems: ArrayList<BotItem>? = ArrayList()
    lateinit var activity : MainActivity
    lateinit var dataBaseFavourites : AppDatabase
    lateinit var itemDao: ItemDao
    var cartItems : ArrayList<CartItem> = ArrayList()

    public var isDarkTheme : Boolean = false

    companion object{
        private var myResources : MyResources? = null

        fun  getInstance() : MyResources{
            if(myResources == null)
                myResources = MyResources()

            return myResources!!
        }
    }

    fun getFirebaseAuth() : FirebaseAuth{
        return firebaseAuth
    }

    fun getFirebaseFirestore() : FirebaseFirestore{
        return firestore
    }
}