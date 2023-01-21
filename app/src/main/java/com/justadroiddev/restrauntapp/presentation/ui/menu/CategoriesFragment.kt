package com.justadroiddev.restrauntapp.presentation.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.databinding.FragmentCategoryBinding
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import com.justadroiddev.restrauntapp.presentation.adapters.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {


    private val viewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter(CategoriesAdapter.CategoryClickListener { category ->
            this.findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToMenuFragment(category))
        })

        viewModel.observeCategories(viewLifecycleOwner) { list ->
            if (list != null){
                when(list){
                    is ResultDomain.Success -> {
                        adapter.submitList(list.data)
                    }
                    is ResultDomain.Error -> {
                        Snackbar.make(requireView(), list.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.apply {
            listCategories.setHasFixedSize(true)
            listCategories.adapter = adapter
        }


    }


}