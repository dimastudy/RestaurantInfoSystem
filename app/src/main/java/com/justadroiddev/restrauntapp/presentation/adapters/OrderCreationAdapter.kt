package com.justadroiddev.restrauntapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.ItemOrderCreatorBinding
import com.justadroiddev.restrauntapp.domain.DishDomain

class OrderCreationAdapter(

) : ListAdapter<DishDomain, OrderCreationAdapter.OrderCreationViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderCreationViewHolder {
        val binding = ItemOrderCreatorBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_creator, parent, false)
        )
        return OrderCreationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderCreationViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
    }

    class OrderCreationViewHolder(private val binding: ItemOrderCreatorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dishDomain: DishDomain) {
            binding.apply {
                dishName.text = "${dishDomain.dishName} х${dishDomain.portion}"
                ingridientsText.text = dishDomain.ingredients
                textCost.text = "Вартість: ${dishDomain.pricePerPortion * dishDomain.portion}$"
                Glide.with(itemView.context)
                    .load(dishDomain.dishImage)
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

    class OrderCreatorSwipeListener(
        private val adapter: OrderCreationAdapter,
        private val action: (dish: DishDomain) -> Unit
    ) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            when(direction){
                ItemTouchHelper.RIGHT -> {
                    val position = viewHolder.adapterPosition
                    val currentDish = adapter.getItem(position)
                    action(currentDish)
                }
            }

        }


    }

}