package org.sabaini.redditmemes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import org.sabaini.redditmemes.R
import org.sabaini.redditmemes.databinding.FragmentFeedBinding
import org.sabaini.redditmemes.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FeedViewModel.Factory(activity.application)).get(FeedViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /* Set the RecyclerView adapter */
        binding.memesList.adapter = MemeListAdapter(MemeListAdapter.OnClickListener {
            viewModel.displayMemeDetail(it)
        })

        /* Open the detail fragment passing the meme clicked */
        viewModel.navigateToSelectedMeme.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToDetailFragment(it))
                viewModel.displayMemeComplete()
            }
        })

        /* Create the Chips after the filters are created in the viewModel */
        viewModel.filters.observe(viewLifecycleOwner, Observer {
            createChips(binding, it)
        })

        /* Refresh the list of memes after a new filter is selected */
        viewModel.selectedFilter.observe(viewLifecycleOwner, Observer {
            viewModel.refresh()
        })

        return binding.root
    }

    private fun createChips(binding: FragmentFeedBinding, filters: List<FeedViewModel.MemesFilter>) {
        val chipGroup = binding.filterList
        val inflator = LayoutInflater.from(chipGroup.context)

        val children = filters.map { filter ->
            val chip = inflator.inflate(R.layout.filter, chipGroup, false) as Chip
            chip.text = filter.value.capitalize()
            chip.tag = filter
            chip.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked) {
                    viewModel.setSelectedFilter(button.tag as FeedViewModel.MemesFilter)
                }
            }
            chip
        }

        chipGroup.removeAllViews()

        for (chip in children) {
            chipGroup.addView(chip)
            if (viewModel.selectedFilter.value == chip.tag as FeedViewModel.MemesFilter) {
                chipGroup.check(chip.id)
            }
        }
    }
}