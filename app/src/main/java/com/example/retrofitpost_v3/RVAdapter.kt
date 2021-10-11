package com.example.retrofitpost_v3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpost_v3.databinding.ActivityMainBinding
import com.example.retrofitpost_v3.databinding.ItemBinding

class RVAdapter: RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {


    }

    private val diffCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
           return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
           return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<Data>
    get() = differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItem.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }


}