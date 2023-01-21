package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.databinding.FragmentOrdersBinding
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import com.justadroiddev.restrauntapp.presentation.adapters.OrdersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : BaseFragment<FragmentOrdersBinding>(FragmentOrdersBinding::inflate) {

    private val viewModel: OrdersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrdersAdapter()

        viewModel.observeOrders(viewLifecycleOwner) { result ->
            if (result != null){
                when(result){
                    is ResultDomain.Success -> {
                        if (result.data.isNotEmpty()){
                            adapter.submitList(result.data)
                            binding.emptyListText.visibility = View.GONE
                        } else {
                            binding.emptyListText.visibility = View.VISIBLE
                        }
                    }
                    is ResultDomain.Error -> {
                        Snackbar.make(requireView(), result.message, Snackbar.LENGTH_LONG).show()
                        binding.emptyListText.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.apply {
            listOrders.adapter = adapter
            listOrders.setHasFixedSize(true)
        }


    }


    override fun onResume() {
        viewModel.initOrders()
        super.onResume()
    }


}