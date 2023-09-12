package com.example.imdbclone.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imdbclone.R
import com.example.imdbclone.databinding.FragmentHomePageBinding
import com.example.imdbclone.fragments.home.adapter.MoviePreviewAdapter

class HomePage : Fragment() {

    private val moviesViewModel:

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private val moviePreviewAdapter: MoviePreviewAdapter by lazy { MoviePreviewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)
        binding.


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}