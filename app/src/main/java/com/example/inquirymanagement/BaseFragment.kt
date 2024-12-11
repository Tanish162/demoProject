package com.example.inquirymanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected lateinit var binding: VB
    private lateinit var toolbar: MaterialToolbar

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
    }

    protected fun toolbarTitle(title: String) {
        toolbar.title = title
    }

    protected fun toolbarAddClick(
        moveForward: Boolean,
        homeFragment: Fragment,
        id: Int,
        bundle: Bundle?
    ) {
        if (moveForward) {
            toolbar.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.add -> {
                        navigationForward(homeFragment, id, bundle, slideLeft())
                    }
                }
                true
            }
        } else {
            toolbar.menu.findItem(R.id.add).setVisible(false)
        }
    }

     private fun slideLeft(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.slide_out)
            .setPopEnterAnim(R.anim.slide_in_right)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
    }

    private fun navigationForward(
        fragment: Fragment,
        id: Int,
        bundle: Bundle?,
        slideLeft: NavOptions
    ) {
        findNavController(fragment).navigate(id, bundle, slideLeft)
    }

    protected fun navigateToBack(fragment: Fragment) {
        findNavController(fragment).navigateUp()
    }
}