package mx.com.questionsstress.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuestionsViewPagerAdapter(fragment: Fragment, private val list: MutableList<Fragment>)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}