package org.sabaini.redditmemes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sabaini.redditmemes.R
import org.sabaini.redditmemes.databinding.ItemLoadStateBinding
import org.sabaini.redditmemes.utilities.LoadState

class LoadStateAdapter : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }

            field = value
        }

    private var itemsCount: Int = 0

    class LoadStateViewHolder(private var binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressBar.visibility = when (LoadState.Loading == loadState) {
                true -> View.VISIBLE
                false -> View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(
        ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bind(loadState)
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_load_state
    }
}