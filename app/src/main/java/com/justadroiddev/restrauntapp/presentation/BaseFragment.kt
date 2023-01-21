package com.justadroiddev.restrauntapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.util.zip.Inflater

open class BaseFragment<VB : ViewBinding>(
    private val inflaterLayout: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflaterLayout.invoke(inflater)
        return binding.root
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun log(message: String){
        Log.e(this::class.java.name, message)
    }


}