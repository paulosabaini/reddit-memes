package org.sabaini.redditmemes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.redditmemes.adapters.LoadStateAdapter
import org.sabaini.redditmemes.adapters.MemeListAdapter
import org.sabaini.redditmemes.databinding.FragmentFeedBinding
import org.sabaini.redditmemes.ui.viewmodels.FeedViewModel
import org.sabaini.redditmemes.utilities.LoadState

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()

    private lateinit var memeListAdapter: MemeListAdapter
    private lateinit var loadStateAdapter: LoadStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView(binding)

        /* Open the detail fragment passing the meme clicked */
        viewModel.navigateToSelectedMeme.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController()
                    .navigate(FeedFragmentDirections.actionFeedFragmentToDetailFragment(it))
                viewModel.displayMemeComplete()
            }
        })

        return binding.root
    }

    private fun setupRecyclerView(binding: FragmentFeedBinding) {
        memeListAdapter = MemeListAdapter(MemeListAdapter.OnClickListener {
            viewModel.displayMemeDetail(it)
        })
        loadStateAdapter = LoadStateAdapter()

        /* Set the RecyclerView adapter */
        binding.memesList.adapter = ConcatAdapter(memeListAdapter, loadStateAdapter)

        binding.memesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                    viewModel.loadMore()
                }
            }
        })
    }
}