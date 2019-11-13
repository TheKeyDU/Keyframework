package com.example.keyframework.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

 class HomePagerFragmentAdapter(fm: FragmentManager, behavior: Int, var list: List<Fragment>) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }

}