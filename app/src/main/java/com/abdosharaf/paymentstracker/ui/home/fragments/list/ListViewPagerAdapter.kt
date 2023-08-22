package com.abdosharaf.paymentstracker.ui.home.fragments.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ListViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListPagerFragment.newInstance(true)
            1 -> ListPagerFragment.newInstance(false)
            else -> ListPagerFragment.newInstance(true)
        }
    }
}