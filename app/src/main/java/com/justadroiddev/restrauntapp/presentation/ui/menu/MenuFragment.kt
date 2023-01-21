package com.justadroiddev.restrauntapp.presentation.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.data.RepositoryImpl
import com.justadroiddev.restrauntapp.databinding.FragmentMenuBinding
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import com.justadroiddev.restrauntapp.presentation.adapters.MenuDishesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate){

    private val viewModel: MenuViewModel by viewModels {
        val category = MenuFragmentArgs.fromBundle(requireArguments()).category
        MenuViewModel.getViewModel(category, factory)
    }

    @Inject lateinit var factory: MenuViewModel.AssistedFactoryMenu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MenuDishesAdapter(MenuDishesAdapter.MenuClickListener { dish ->
            this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToDishDetailFragment(dish))
        })

        viewModel.observeMenu(viewLifecycleOwner) { menuList ->
            if (menuList != null){
                when(menuList){
                    is ResultDomain.Success -> {
                        adapter.submitList(menuList.data)
                    }
                    is ResultDomain.Error -> {
                        Snackbar.make(requireView(), menuList.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.apply {
            listMenu.setHasFixedSize(true)
            listMenu.adapter = adapter
        }


    }



}