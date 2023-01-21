package com.justadroiddev.restrauntapp.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.justadroiddev.restrauntapp.databinding.FragmentDishBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import kotlin.math.min


class DishDetailFragment : BaseFragment<FragmentDishBinding>(FragmentDishBinding::inflate) {


    private val viewModel: DishDetailViewModel by viewModels {
        val dish = DishDetailFragmentArgs.fromBundle(requireArguments()).dish
        DishDetailViewModel.Factory(dish)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeDish(viewLifecycleOwner) { dishDomain ->
            binding.apply {
                textDish.text = dishDomain.dishName
                textDetails.text = dishDomain.ingredients
                ratingBar.rating = dishDomain.rating.toFloat()
                priceForDish.text = "Вартість: ${dishDomain.pricePerPortion * dishCounter.text.toString().toInt()}$"
                Glide.with(requireView())
                    .load(dishDomain.dishImage)
                    .centerCrop()
                    .into(imageDish)
                plusBtn.setOnClickListener {
                    val count = dishCounter.text.toString().toInt()
                    val countNext = count.plus(1)
                    dishCounter.text = "$countNext"
                    priceForDish.text =  "Вартість: ${dishDomain.pricePerPortion * countNext}$"
                }
                minusBtn.setOnClickListener {
                    val count = dishCounter.text.toString().toInt()
                    val countPrev = count.minus(1)
                    if (count > 1) {
                        dishCounter.text = "$countPrev"
                        priceForDish.text = "Вартість: ${dishDomain.pricePerPortion * countPrev}$"
                    }

                }
                submitBtn.setOnClickListener {
                    val count = dishCounter.text.toString().toInt()
                    val message = noteText.text.toString()
                    viewModel.orderDish(count, message)
                    this@DishDetailFragment.findNavController()
                        .navigate(DishDetailFragmentDirections.actionDishDetailFragmentToCategoriesFragment())
                }
            }
        }

    }

}