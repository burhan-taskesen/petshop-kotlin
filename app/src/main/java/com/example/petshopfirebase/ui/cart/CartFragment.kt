package com.example.petshopfirebase.ui.cart

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.withCreated
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petshopfirebase.R
import com.example.petshopfirebase.adapter.rvCartMain
import com.example.petshopfirebase.core.MyResources
import com.example.petshopfirebase.databinding.FragmentCartBinding
import com.example.petshopfirebase.rest.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.scalars.ScalarsConverterFactory

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater)

        binding.fabCartFragmentOrder.setOnClickListener(){
            giveOrder()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    fun setRecycler(){
        if(MyResources.getInstance().cartItems.size != 0){
            binding.rvCartFragmentMain.adapter = rvCartMain(MyResources.getInstance().cartItems,this)
            binding.rvCartFragmentMain.layoutManager = LinearLayoutManager(requireContext())
            binding.tvCartFragmentEmpty.visibility = View.INVISIBLE
        }
        else{
            binding.rvCartFragmentMain.layoutManager = null
            binding.tvCartFragmentEmpty.visibility = View.VISIBLE
        }
    }

    fun giveOrder(){
        /*
        CoroutineScope(Dispatchers.IO).launch {
            var response = (URL("https://kotlin-deneme-backend.herokuapp.com/api").openConnection() as HttpsURLConnection)
            var a = BufferedReader(InputStreamReader(response.inputStream))
            val lines = a.readLines().forEach(){
                Toast.makeText(requireContext(),it,0).show()
            }
        }
        //Bu yöntemin nasıl çalışacağını merak ettiğim için buraya ekledim. Yorum satırları kaldırılınca direkt olarak çalışabilir durumda
        */
        if(MyResources.getInstance().cartItems.size == 0){
            Toast.makeText(requireContext(),"Sepetinize ürün eklemeyi unutmuş olabilirsiniz", Toast.LENGTH_SHORT).show()
            return
        }

        var token = getString(R.string.token)
        var chatId = getString(R.string.chatId)
        var message = ""
        var total = 0.0

        MyResources.getInstance().cartItems.forEach {
            message = message + it.item.name + " x " + it.piece + ","
            total += it.piece * it.item.price
        }
        message += "total : " + total.toString()

        var api = Retrofit.Builder().baseUrl("https://api.telegram.org/bot" + token + "/").addConverterFactory(ScalarsConverterFactory.create()).build().create(api::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            api.order(chatId,message).enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    CoroutineScope(Dispatchers.Main).launch {
                        //println(response.body())
                        if(response.code() == 200)
                            Toast.makeText(requireContext(),"Siparişiniz verildi.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    println("Hata : " + t.localizedMessage)
                    Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}