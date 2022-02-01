package com

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velmurugan.mvvmretrofitrecyclerviewkotlin.*
import kotlinx.android.synthetic.main.fragment_blank.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    companion object {
        fun newInstance() = BlankFragment()

    }

    private fun init(){
        initViewModel()
        initData()
        initViewModelObserver()
        initAdapter()
    }
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var postListViewModel: MainViewModel
    private fun initViewModel(){
        postListViewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        println("initviewmodel")
    }

    private fun initData(){
        postListViewModel.getAllMovies()
    }

    private fun initViewModelObserver(){
        postListViewModel.movieList.observe(viewLifecycleOwner, {
            onGetPostListResponse(it)
        })
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    val adapter = MainAdapter()
    private fun initAdapter() {
        println("initadapter")
        linearLayoutManager = LinearLayoutManager(activity , RecyclerView.VERTICAL , false)
        movie_recycler.adapter = adapter
        movie_recycler.layoutManager = linearLayoutManager
    }

    private fun onGetPostListResponse(resultResponse : List<Movie>?){
        if (resultResponse != null) {
            adapterListSubmit(resultResponse)
        }
    }

    lateinit var data : ArrayList<*>
    private fun adapterListSubmit(resultResponse: List<Movie>?) {
        data = resultResponse as ArrayList<*>
        adapter.submitList(data)
    }
}