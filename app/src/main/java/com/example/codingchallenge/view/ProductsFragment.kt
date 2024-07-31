package com.example.codingchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingchallenge.databinding.FragmentProductsBinding
import com.example.codingchallenge.view.adapter.ProductsAdapter
import com.example.codingchallenge.viewmodel.ProductsViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val productsViewModel = ProductsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchNews()
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
            val headlines = productsViewModel.getProducts()
            withContext(Dispatchers.Main) {
                binding.newsProgressBar.visibility = View.GONE
                binding.newsProgressBar.isIndeterminate = false
                headlines?.let {
                    if (it.isNotEmpty()) {
                        binding.recyclerView.adapter = ProductsAdapter(it)
                    }
                }
            }
        }
    }

    private fun setupViews() {
        binding.recyclerView.adapter = ProductsAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}