package com.example.codingchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.databinding.FragmentNewsBinding
import com.example.codingchallenge.view.NewsAdapter
import com.example.codingchallenge.viewmodel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsFragment : Fragment() {

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
        _recyclerView =  view.findViewById(R.id.recycler_view)
        _recyclerView?.adapter = NewsAdapter(listOf())
        _recyclerView?.layoutManager = LinearLayoutManager(context)
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        fetchNews()
    }

    fun fetchNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val headlines = newsViewModel.getHeadlines()
            headlines?.let {
                if (it.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _recyclerView?.adapter = NewsAdapter(it)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}