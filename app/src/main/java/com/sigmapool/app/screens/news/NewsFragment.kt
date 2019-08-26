package com.sigmapool.app.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sigmapool.app.databinding.FragmentNewsBinding
import com.sigmapool.app.screens.news.vm.NewsVM
import com.sigmapool.app.utils.customViews.InnerFragment

class NewsFragment : InnerFragment(), INewsFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        val vm = NewsVM(this)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }
}