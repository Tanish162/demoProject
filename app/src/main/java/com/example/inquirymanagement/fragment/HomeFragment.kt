package com.example.inquirymanagement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inquirymanagement.BaseFragment
import com.example.inquirymanagement.R
import com.example.inquirymanagement.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbarTitle(getString(R.string.home))
            toolbarAddClick(true,this@HomeFragment,R.id.action_homeFragment_to_formFragment,null)
            /*       navigationForward(
                       this@HomeFragment,
                       R.id.action_homeFragment_to_formFragment,
                       null,
                       slideLeft()
                   )*/
        }
    }

}