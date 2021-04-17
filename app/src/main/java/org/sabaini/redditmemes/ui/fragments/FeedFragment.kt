package org.sabaini.redditmemes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.redditmemes.adapters.MemeListAdapter
import org.sabaini.redditmemes.databinding.FragmentFeedBinding
import org.sabaini.redditmemes.ui.viewmodels.FeedViewModel

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /* Set the RecyclerView adapter */
        binding.memesList.adapter = MemeListAdapter(MemeListAdapter.OnClickListener {
            viewModel.displayMemeDetail(it)
        })

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
}