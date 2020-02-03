package com.yassou.postpartumapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerAdapter(fm: FragmentManager, private val fragments: ArrayList<Fragment>) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return this.fragments[position]
    }


    override fun getCount(): Int {
        return this.fragments.size
    }
}
