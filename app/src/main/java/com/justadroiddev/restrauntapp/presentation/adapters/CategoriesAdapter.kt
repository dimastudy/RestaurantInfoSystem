package com.justadroiddev.restrauntapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.ItemCategoryBinding
import com.justadroiddev.restrauntapp.domain.CategoryDomain

class CategoriesAdapter(
    private val clickListener: CategoryClickListener
) : ListAdapter<CategoryDomain, CategoriesAdapter.CategoriesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
        holder.itemView.setOnClickListener{
            clickListener.onClick(category)
        }
    }


    class CategoriesViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryDomain: CategoryDomain) {
            binding.apply {
                nameCategory.text = categoryDomain.name
                Glide.with(itemView.context)
                    .load(categoryDomain.categoryImage)
                    .centerCrop()
                    .into(imageCategory)
            }
        }

    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<CategoryDomain>() {
            override fun areItemsTheSame(
                oldItem: CategoryDomain,
                newItem: CategoryDomain
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CategoryDomain,
                newItem: CategoryDomain
            ): Boolean =
                oldItem == newItem

        }
    }

    class CategoryClickListener(private val action: (category: CategoryDomain) -> Unit) {
        fun onClick(category: CategoryDomain) = action(category)
    }

}