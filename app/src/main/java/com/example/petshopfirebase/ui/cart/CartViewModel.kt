package com.example.petshopfirebase.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    public var tv_cart_tmp = MutableLiveData<String>().apply{
        this.value = "Şuanda alışveriş sepetindesiniz."
    }
}