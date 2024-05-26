package com.example.codingchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.R
import com.example.codingchallenge.databinding.FragmentNewsBinding
import com.example.codingchallenge.model.Article
import com.example.codingchallenge.view.adapter.NewsAdapter
import com.example.codingchallenge.viewmodel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsFragment : Fragment(), NewsAdapter.NewsItemClickListener {

    private var _binding: FragmentNewsBinding? = null
    private var _recyclerView: RecyclerView? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val newsViewModel = NewsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        fetchNews()
    }

    private fun fetchNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val headlines = newsViewModel.getHeadlines()
            headlines?.let {
                if (it.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _recyclerView?.adapter = NewsAdapter(it, this@NewsFragment)
                    }
                }
            }
        }
    }

    private fun setupViews(view: View) {
        _recyclerView =  view.findViewById(R.id.recycler_view)
        _recyclerView?.adapter = NewsAdapter(listOf(), this)
        _recyclerView?.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNewsItemClicked(item: Article) {
        val bundle = bundleOf("article" to item)
        findNavController().navigate(R.id.action_NewsFragment_to_ArticleFragment, bundle)
    }
}