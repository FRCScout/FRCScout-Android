package com.alphadevelopmentsolutions.frcscout.ui.fragments.team

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.viewpager.widget.ViewPager
import com.alphadevelopmentsolutions.frcscout.R
import com.alphadevelopmentsolutions.frcscout.adapters.FragmentViewPagerAdapter
import com.alphadevelopmentsolutions.frcscout.classes.ViewPagerFragment
import com.alphadevelopmentsolutions.frcscout.data.models.Team
import com.alphadevelopmentsolutions.frcscout.ui.fragments.matchlist.MatchListFragment
import com.alphadevelopmentsolutions.frcscout.ui.fragments.medialist.RobotMediaListFragment
import com.alphadevelopmentsolutions.frcscout.ui.fragments.robotinfo.RobotInfoFragment

class TeamViewModel(
    application: Application,
    childFragmentManager: FragmentManager,
    val lifecycleOwner: LifecycleOwner,
    val team: Team
) : AndroidViewModel(application) {
    private val context = application

    val showFab: MutableLiveData<Boolean> = MutableLiveData()

    val viewPagerAdapter =
        FragmentViewPagerAdapter(
            application as FragmentActivity
        )

    val viewPagerOnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                showFab.value = position == 2
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        }

    private val matchListFragment by lazy {
        MatchListFragment.newInstance(true, team)
    }

    private val robotInfoFragment by lazy {
        RobotInfoFragment.newInstance(team)
    }

    private val robotMediaListFragment by lazy {
        RobotMediaListFragment.newInstance(team)
    }

    init {
        viewPagerAdapter.addFragment(
            ViewPagerFragment(
                context.getString(R.string.matches),
                matchListFragment
            )
        )

        viewPagerAdapter.addFragment(
            ViewPagerFragment(
                context.getString(R.string.info),
                robotInfoFragment
            )
        )

        viewPagerAdapter.addFragment(
            ViewPagerFragment(
                context.getString(R.string.media),
                robotMediaListFragment
            )
        )
    }

    fun createMedia() {
        robotMediaListFragment.createMedia()
    }
}