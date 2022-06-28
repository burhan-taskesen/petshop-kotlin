package com.example.petshopfirebase.core

import com.example.petshopfirebase.MainActivity
import com.example.petshopfirebase.dataAccess.AppDatabase
import com.example.petshopfirebase.dataAccess.ItemDao
import com.example.petshopfirebase.entities.BotItem
import com.example.petshopfirebase.entities.TopItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyResources() {

    private var firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    public var favItems: ArrayList<BotItem>? = ArrayList()
    public lateinit var activity : MainActivity
    public lateinit var dataBase : AppDatabase
    public lateinit var itemDao: ItemDao
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