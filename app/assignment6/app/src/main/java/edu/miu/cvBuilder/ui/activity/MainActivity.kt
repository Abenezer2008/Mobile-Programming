package edu.miu.cvBuilder.ui.activity

import CVBuilderApp.R
import CVBuilderApp.databinding.ActivityMainBinding
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.miu.cvBuilder.adapter.ViewAdapter
import edu.miu.cvBuilder.domain.Work
import edu.miu.cvBuilder.ui.dialog.DialogCommunicator
import edu.miu.cvBuilder.ui.dialog.WorkDialogCommunicator
import edu.miu.cvBuilder.helper.AppUtils


class MainActivity : AppCompatActivity(), DialogCommunicator, WorkDialogCommunicator {

    private lateinit var binding: ActivityMainBinding
    private var sharedPref: SharedPreferences = AppUtils.getSharedPref()
    private lateinit var adapter: ViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val theme = AppUtils.getPref(getString(R.string.saved_theme))
        val user = AppUtils.getPref(getString(R.string.login_user_key))
        if (theme != null) AppUtils.decideTheme(theme)

        showWorkDialog()
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.home_tab)
                1 -> tab.text = getString(R.string.aboutme_tab)
                2 -> tab.text = getString(R.string.work_tab)
                3 -> tab.text = getString(R.string.contact_tab)
            }
        }.attach()


        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_main_logout -> {
                    finish()
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }


    override fun onChangeTheme(theme: String) {
        with(sharedPref.edit()) {
            putString(getString(R.string.saved_theme), theme)
            apply()
        }
        AppUtils.decideTheme(theme)
    }

    private fun showWorkDialog() {
        adapter = ViewAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter
    }

    override fun onAddWOrk(work: Work) {
        if (::adapter.isInitialized) {
            adapter.addWork(work)
        } else {
            showWorkDialog()
            adapter.addWork(work)
        }
    }

}