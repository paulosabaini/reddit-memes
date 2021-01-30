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

        return binding.root
    }
}