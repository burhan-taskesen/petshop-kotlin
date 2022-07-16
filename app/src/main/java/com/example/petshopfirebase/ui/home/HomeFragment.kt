package com.example.petshopfirebase.ui.home

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petshopfirebase.adapter.rvBotAdapter
import com.example.petshopfirebase.adapter.rvTopAdapter
import com.example.petshopfirebase.util.MyResources
import com.example.petshopfirebase.databinding.FragmentHomeBinding
import com.example.petshopfirebase.entities.BotItem
import com.example.petshopfirebase.entities.TopItem
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class HomeFragment : Fragment() {

    public lateinit var binding: FragmentHomeBinding
    

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("Debug mesaj","onAttach") //TODO: Debug mod bitince burası kaldırılacak
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Debug mesaj","onCreate") //TODO: Debug mod bitince burası kaldırılacak
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        Log.d("Debug mesaj","oncreateview") //TODO: Debug mod bitince burası kaldırılacak
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** temanın karanlık olup olmamasına göre gerekli görsel düzenlemeleri yapar **/
        MyResources.getInstance().isDarkTheme = isDarkThemeOn()
        if(!MyResources.getInstance().isDarkTheme){
            binding.rvTop.setBackgroundColor(Color.WHITE)
            binding.rvBot.setBackgroundColor(Color.WHITE)
        }


        setTopRecycle(binding.rvTop)
        setBotRecycle()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Debug mesaj","onStart") //TODO: Debug mod bitince burası kaldırılacak
    }

    fun isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    } //Tema kontrol edici

    fun setBotRecycle(path : String = "mamalar"){
        binding.rvBot.adapter = rvBotAdapter(ArrayList())
        MyResources.getInstance().getFirebaseFirestore().collection(path).addSnapshotListener(object :
            EventListener<QuerySnapshot?> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error==null){
                    var myBotList = ArrayList<BotItem>()
                    for(tmp : DocumentSnapshot in value!!.documents) {
                        myBotList.add(
                            BotItem(
                                tmp.getString("name")!!,
                                tmp.getString("image")!!,
                                tmp.getDouble("price")!!,
                                tmp.getString("uuid")!!
                            )
                        )
                    }

                    binding.rvBot.adapter = rvBotAdapter(myBotList)
                    binding.rvBot.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
                }
            }
        })
    }

    private fun setTopRecycle(rv: RecyclerView) {
        MyResources.getInstance().getFirebaseFirestore().collection("items").addSnapshotListener(object : EventListener<QuerySnapshot?> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error==null){
                    var myTopList = ArrayList<TopItem>()
                    for(tmp : DocumentSnapshot in value!!.documents) {
                        myTopList.add(TopItem(tmp.getString("name")!!, tmp.getString("image")!!))
                    }

                    rv.adapter = rvTopAdapter(myTopList,this@HomeFragment)
                    rv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                }
            }
        })
    }
}