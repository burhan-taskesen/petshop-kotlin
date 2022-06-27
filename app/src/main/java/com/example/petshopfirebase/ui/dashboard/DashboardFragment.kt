package com.example.petshopfirebase.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petshopfirebase.databinding.FragmentCartBinding


class DashboardFragment : Fragment() {

    //private var _binding: FragmentDashboardBinding? = null
    private lateinit var dashboardViewModel : DashboardViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        //_binding = FragmentDashboardBinding.inflate(inflater, container, false)
        //val root: View = binding.root

        //val textView: TextView = binding.textDashboard
        //dashboardViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}
        return FragmentCartBinding.inflate(inflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.textDashboard.setOnClickListener(){
        //    dashboardViewModel.setValue("hey")
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}