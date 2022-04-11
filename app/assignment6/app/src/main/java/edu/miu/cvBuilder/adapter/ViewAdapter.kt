package edu.miu.cvBuilder.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import edu.miu.cvBuilder.domain.Work
import edu.miu.cvBuilder.ui.fragments.AboutFragment
import edu.miu.cvBuilder.ui.fragments.ContactFragment
import edu.miu.cvBuilder.ui.fragments.HomeFragment
import edu.miu.cvBuilder.ui.fragments.WorkFragment

class ViewAdapter(fm:FragmentManager, lc:Lifecycle) : FragmentStateAdapter(fm,lc) {
    override fun getItemCount(): Int = 4
    val workFragment = WorkFragment()
    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0-> HomeFragment()
            1-> AboutFragment()
            2-> workFragment
            3-> ContactFragment()
            else-> Fragment()
        }
    }

    fun addWork(work: Work){
        workFragment.onAddWOrk(work)
    }
}

