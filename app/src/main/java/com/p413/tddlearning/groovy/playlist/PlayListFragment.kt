package com.p413.tddlearning.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.p413.tddlearning.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://63e32ccfc919fe386c01baef.mockapi.io/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api = retrofit.create(PlayListApi::class.java)
    private val service = PlayListService(api)
    private val repository = PlayListRepository(service)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setUpViewModel()

        viewModel.playList.observe(this as LifecycleOwner) { playLists ->
            if (playLists.getOrNull() != null) {
                setUpRecyclerView(view, playLists.getOrNull()!!)
            } else {
                // TODO error handling
            }
        }

        return view
    }

    private fun setUpRecyclerView(
        view: View?,
        playLists: List<PlayList>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlayListRecyclerViewAdapter(playLists)
        }
    }

    private fun setUpViewModel() {
        viewModelFactory = PlayListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlayListFragment().apply {}
    }
}