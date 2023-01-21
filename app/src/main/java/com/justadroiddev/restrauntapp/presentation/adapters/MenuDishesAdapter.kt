package com.justadroiddev.restrauntapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.ItemDishBinding
import com.justadroiddev.restrauntapp.domain.DishDomain

class MenuDishesAdapter(
    private val clickListener: MenuClickListener
) : ListAdapter<DishDomain, MenuDishesAdapter.MenuDishesViewHolder>(
    DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDishesViewHolder {
        val binding = ItemDishBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_dish,
                    parent,
                    false
                )
        )
        return MenuDishesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuDishesViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
        holder.itemView.setOnClickListener {
            clickListener.onClick(dish)
        }
    }

    class MenuDishesViewHolder(private val binding: ItemDishBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: DishDomain){
            binding.apply {
                dishName.text = dish.dishName
                ingridientsText.text = dish.ingredients
                textCost.text = dish.pricePerPortion.toString()
                Glide.with(itemView.context)
                    .load(dish.dishImage)
                    .centerCrop()
                    .into(imageDish)
            }
        }

    }



    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<DishDomain>() {
            override fun areItemsTheSame(oldItem: DishDomain, newItem: DishDomain): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DishDomain, newItem: DishDomain): Boolean =
                oldItem == newItem

        }
    }

    class MenuClickListener(private val clickListener: (dishDomain: DishDomain) -> Unit){
        fun onClick(dishDomain: DishDomain) = clickListener(dishDomain)
    }


}