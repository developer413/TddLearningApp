package com.p413.tddlearning.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.p413.tddlearning.databinding.FragmentPlaylistBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayListFragment : Fragment() {

    private lateinit var playlistBinding: FragmentPlaylistBinding

    private lateinit var viewModel: PlayListViewModel

    @Inject
    lateinit var viewModelFactory: PlayListViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        playlistBinding = FragmentPlaylistBinding.inflate(inflater, container, false)

        setUpViewModel()

        return playlistBinding.root
    }

    private fun setUpRecyclerView(
        playLists: List<PlayList>
    ) {
        playlistBinding.rvPlayList.layoutManager = LinearLayoutManager(context)
        playlistBinding.rvPlayList.adapter = MyPlayListRecyclerViewAdapter(playLists) { id ->
            val action = PlayListFragmentDirections.actionPlayListFragmentToDetailsFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
        viewModel.playList.observe(this as LifecycleOwner) { playLists ->
            if (playLists.getOrNull() != null) {
                setUpRecyclerView(playLists.getOrNull()!!)
            } else {
                // TODO error handling
            }
        }

        viewModel.loader.observe(viewLifecycleOwner) {
            playlistBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlayListFragment().apply {}
    }
}