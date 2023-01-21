package com.justadroiddev.restrauntapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.ItemOrderBinding
import com.justadroiddev.restrauntapp.domain.OrderResponseDomain
import com.justadroiddev.restrauntapp.domain.OrderStatus

class OrdersAdapter(

) : ListAdapter<OrderResponseDomain, OrdersAdapter.OrdersViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemOrderBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
        holder.itemView.setOnClickListener {

        }
    }

    class OrdersViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(order: OrderResponseDomain) {
            var priceForOrder = 0.0
            val list = order.dishes
            for (dish in list){
                priceForOrder += (dish.pricePerPortion*dish.portion)
            }
            binding.apply {
                orderNumber.text = "Замовлення №${order.id.toString()}"
                if (order.isDelivery){
                    orderType.text = "Замовлення на доставку"
                    addressOrNumberTableText.text = order.address
                } else {
                    orderType.text = "Бронювання столика"
                    addressOrNumberTableText.text = "Столик №${order.tableNumber}"
                }
                dateCreationText.text = order.createdDate
                priceOrderText.text = "Вартість: ${priceForOrder.toString()}$"
                when(order.status){
                    OrderStatus.Ordered -> {
                        statusText.text = "Замовлено"
                        statusContainer.setCardBackgroundColor(itemView.resources.getColor(R.color.white, null))
                    }
                    OrderStatus.Making -> {
                        statusText.text = "Виконується"
                        statusContainer.setCardBackgroundColor(itemView.resources.getColor(R.color.soft_orange, null))
                    }
                    OrderStatus.Made -> {
                        statusText.text = "Виконано"
                        statusContainer.setCardBackgroundColor(itemView.resources.getColor(R.color.soft_green, null))
                    }
                    OrderStatus.Delivered -> {
                        statusText.text = "Доставлено"
                        statusContainer.setCardBackgroundColor(itemView.resources.getColor(R.color.soft_purple, null))
                    }
                    OrderStatus.Denied -> {
                        statusText.text = "Відмовлено"
                        statusContainer.setCardBackgroundColor(itemView.resources.getColor(R.color.red, null))
                    }
                }
            }
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<OrderResponseDomain>() {
            override fun areItemsTheSame(
                oldItem: OrderResponseDomain,
                newItem: OrderResponseDomain
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: OrderResponseDomain,
                newItem: OrderResponseDomain
            ): Boolean = oldItem == newItem

        }
    }



}