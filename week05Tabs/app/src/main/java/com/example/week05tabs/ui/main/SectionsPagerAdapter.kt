package com.example.week05tabs.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.week05tabs.R
import layout.GradesFragment
import layout.StudentFragment

private val TAB_TITLES = arrayOf(
    "Students",
    "Grades"
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position)
        {
            0 -> StudentFragment()
            1 -> GradesFragment()
            else -> StudentFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}