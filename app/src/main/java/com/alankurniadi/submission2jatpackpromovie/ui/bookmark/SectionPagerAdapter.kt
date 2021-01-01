package com.alankurniadi.submission2jatpackpromovie.ui.bookmark

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.ui.bookmark.movie.MovieBookmarkFragment
import com.alankurniadi.submission2jatpackpromovie.ui.bookmark.trending.BookmarkTrendingFragment
import com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv.TvBookmarkFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = intArrayOf(R.string.tab_trending, R.string.tab_movie, R.string.tab_tv)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = BookmarkTrendingFragment()
            1 -> fragment = MovieBookmarkFragment()
            2 -> fragment = TvBookmarkFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = TAB_TITLES.size

}