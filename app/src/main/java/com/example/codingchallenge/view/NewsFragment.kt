package com.example.codingchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingchallenge.MainActivity
import com.example.codingchallenge.R
import com.example.codingchallenge.databinding.FragmentNewsBinding
import com.example.codingchallenge.model.Article
import com.example.codingchallenge.utils.SpeechRecognitionUtils
import com.example.codingchallenge.view.adapter.NewsAdapter
import com.example.codingchallenge.viewmodel.NewsViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsFragment : Fragment(), NewsAdapter.NewsItemClickListener,
    SpeechRecognitionUtils.VoiceRequestListener {

    private var _binding: FragmentNewsBinding? = null
    private var headlineCountryCode: String = "us"
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
        setupViews()
        fetchNews()
        (activity as MainActivity).setVoiceRequestListener(this)
    }

    private fun fetchNews() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            println("ERROR found while fetching")
            throwable.printStackTrace()
        }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            withContext(Dispatchers.Main) {
                binding.newsProgressBar.visibility = View.VISIBLE
                binding.newsProgressBar.isIndeterminate = true
            }
            val headlines = newsViewModel.getHeadlines(headlineCountryCode)
            withContext(Dispatchers.Main) {
                binding.newsProgressBar.visibility = View.GONE
                binding.newsProgressBar.isIndeterminate = false
                headlines?.let {
                    if (it.isNotEmpty()) {
                        binding.recyclerView.adapter = NewsAdapter(it, this@NewsFragment)
                    }
                }
            }
        }
    }

    private fun setupViews() {
        binding.recyclerView.adapter = NewsAdapter(listOf(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        (activity as MainActivity).setVoiceRequestListener(null)
        super.onDestroyView()
        _binding = null
    }

    override fun onNewsItemClicked(item: Article) {
        val bundle = bundleOf("article" to item)
        //TODO - add a shared animation between the imageViews.
        findNavController().navigate(R.id.action_NewsFragment_to_ArticleFragment, bundle)
    }

    override fun onNewVoiceCommand(command: String) {
        //TODO - more command options, combinations, keyword-based parameters
        when (command) {
            "refresh", "reload" -> {
                fetchNews()
            }
            "united states" -> {
                headlineCountryCode = "us"
                fetchNews()
            }
            "germany" -> {
                headlineCountryCode = "de"
                fetchNews()
            }
            "france" -> {
                headlineCountryCode = "fr"
                fetchNews()
            }
        }
    }
}