package com.p413.tddlearning.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.p413.tddlearning.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayListDetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding

    private val args: PlayListDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: PlayListDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlayListDetailsViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)


        val id = args.playListId

        viewModel.getPlayListDetails(id)

        setUpViewModel()
        return detailsBinding.root
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListDetailsViewModel::class.java]
        viewModel.playListDetails.observe(viewLifecycleOwner) { playListDetails ->
            if (playListDetails.getOrNull() != null) {
                detailsBinding.playlistName.text = playListDetails.getOrNull()!!.name
                detailsBinding.playlistDetails.text = playListDetails.getOrNull()!!.details
            }

        }
    }

}