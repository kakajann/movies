package com.kakajann.movies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kakajann.movies.R

data class FragmentItem(
    val title: String,
    val fragment: Fragment
)

class HomeFragment : Fragment() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container,   false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val fragments = setOf<FragmentItem>(
            FragmentItem(getString(R.string.common_movies), MoviesFragment()),
            FragmentItem(getString(R.string.common_tv_series), TvSeriesFragment())
        )

        homeViewPagerAdapter = HomeViewPagerAdapter(fragments, this)
        viewPager = view.findViewById(R.id.vp2_home)
        viewPager.adapter = homeViewPagerAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tl_home)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.text = fragments.elementAt(position).title
        }.attach()
    }
}

class HomeViewPagerAdapter(
    private val fragments: Set<FragmentItem>,
    activity: HomeFragment
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.elementAt(position).fragment
    }
}