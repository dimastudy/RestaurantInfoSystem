package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.FragmentOrderCreatorBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import com.justadroiddev.restrauntapp.presentation.adapters.OrderCreationAdapter
import com.justadroiddev.restrauntapp.presentation.ui.findTopNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderCreatorFragment : BaseFragment<FragmentOrderCreatorBinding>(FragmentOrderCreatorBinding::inflate) {

    private val viewModel: OrderCreatorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderCreationAdapter()

        val itemCallback = OrderCreationAdapter.OrderCreatorSwipeListener(adapter){ dish ->
            viewModel.removeDish(dish)
        }
        val touchHelper = ItemTouchHelper(itemCallback)

        viewModel.observeOrder(viewLifecycleOwner){ list ->
            if (!list.isNullOrEmpty()){
                binding.messageText.visibility = View.GONE
                adapter.submitList(list)
            } else {
                binding.messageText.visibility = View.VISIBLE
            }
        }



        binding.apply {
            listDishes.setHasFixedSize(true)
            listDishes.adapter = adapter
            touchHelper.attachToRecyclerView(listDishes)
            addDishBtn.setOnClickListener {
                this@OrderCreatorFragment.findNavController().navigate(R.id.menu_nav)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_order_creator, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.actioOrderSubmit -> {
                        this@OrderCreatorFragment.findNavController().navigate(OrderCreatorFragmentDirections.actionOrderCreatorFragmentToOrderSubmitFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        Log.e("OrderCreator", "Resume, ${OrderCreator.takeDishes().size}")
        viewModel.updateListDishes()
        super.onResume()
    }

    override fun onPause() {
        Log.e("OrderCreator", "Pause, ${OrderCreator.takeDishes().size}")
        super.onPause()
    }

}