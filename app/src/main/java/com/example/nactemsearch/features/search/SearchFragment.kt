package com.example.nactemsearch.features.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nactemsearch.R
import com.example.nactemsearch.domain.util.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        const val TAG = "SearchFragment"
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchBar: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var exception: TextView
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        bindUI(view)
        searchAdapter = SearchAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = searchAdapter
        }
        bindQueryTextListener()
        bindCollector()
        return view
    }

    private fun bindQueryTextListener() {
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getList(it.trim())
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun bindCollector() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchList.collect {
                    when (it) {
                        is Response.Error -> {
                            searchAdapter.submitList(emptyList())
                            Log.d(TAG, "bindCollector: ${it.errorMessage}")
                            exception.text = it.errorMessage
                            exception.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }

                        is Response.Loading -> {
                            searchAdapter.submitList(emptyList())
                            exception.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }

                        is Response.Success -> {
                            it.data?.let { lfList ->
                                searchAdapter.submitList(lfList)
                            }
                            exception.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun bindUI(view: View) {
        searchBar = view.findViewById(R.id.search_bar)
        searchBar.queryHint = "Search here..."
        searchBar.isIconifiedByDefault = false
        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
        exception = view.findViewById(R.id.exception)
    }

}