package org.sabaini.redditmemes.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.redditmemes.databinding.FragmentDetailBinding
import org.sabaini.redditmemes.ui.viewmodels.DetailViewModel

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this

        val meme = DetailFragmentArgs.fromBundle(requireArguments()).selectedMeme

        viewModel.setSelectedMeme(meme)

        binding.viewModel = viewModel

        binding.detailViewPost.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.reddit.com${meme.permalink}")
            )
            startActivity(i)
        }

        return binding.root
    }
}