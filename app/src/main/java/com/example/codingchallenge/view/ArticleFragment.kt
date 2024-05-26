package com.example.codingchallenge.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.codingchallenge.R
import com.example.codingchallenge.databinding.FragmentArticleBinding
import com.example.codingchallenge.extension.formatted
import com.example.codingchallenge.model.Article
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private var _article: Article? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        _article = arguments?.getSerializable("article") as Article?
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateArticle()
    }

    private fun populateArticle() {
        if (_article == null) {
            findNavController().popBackStack()
        } else {
            binding.articleTitle.text = _article!!.title
            binding.articleDescription.text = _article!!.description
            binding.articleAuthor.text = _article!!.author
            binding.articleDate.text = _article!!.publishedAt?.formatted("HH:mm")
            if (_article!!.imageUrl != null) {
                Picasso.get()
                    .load(_article!!.imageUrl)
                    .into(binding.articleImage)
            } else {
                binding.articleImage.visibility = View.GONE
            }
            binding.openArticleButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(_article!!.url)))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}