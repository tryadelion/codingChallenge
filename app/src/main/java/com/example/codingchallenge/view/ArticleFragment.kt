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
import com.example.codingchallenge.model.Article
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private var _article: Article? = null
    private lateinit var articleImage: ImageView
    private lateinit var articleTitle: TextView
    private lateinit var articleDescription: TextView
    private lateinit var articleButton: Button

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
        setupViews(view)
        populateArticle()
    }

    private fun populateArticle() {
        if (_article == null) {
            findNavController().popBackStack()
        } else {
            articleTitle.text = _article!!.title
            articleDescription.text = _article!!.description

            if (_article!!.imageUrl != null) {
                Picasso.get()
                    .load(_article!!.imageUrl)
                    .into(articleImage)
            } else {
                articleImage.visibility = View.GONE
            }

            articleButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(_article!!.url)))
            }
        }
    }

    private fun setupViews(view: View) {
        articleImage = view.findViewById(R.id.articleImage)
        articleTitle = view.findViewById(R.id.articleTitle)
        articleDescription = view.findViewById(R.id.articleDescription)
        articleButton = view.findViewById(R.id.openArticleButton)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}