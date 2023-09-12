package com.example.imdbclone.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imdbclone.adapters.TabsAdapter
import com.example.imdbclone.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val tabList = arrayListOf<String>(
        "Home Page",
        "Search Page"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.viewPager.adapter = TabsAdapter(parentFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}