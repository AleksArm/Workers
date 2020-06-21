package com.highestaim.workers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.highestaim.workers.R
import com.highestaim.workers.di.DaggerAppComponent
import com.highestaim.workers.ui.fragments.WorkersFragment
import com.highestaim.workers.util.Utils.isOnline
import com.highestaim.workers.util.replaceFragment
import com.highestaim.workers.viewmodel.WorkersUpdateViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val toolbar: Toolbar by lazy { mainToolbar }

    private lateinit var workersUpdateViewModel: WorkersUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        DaggerAppComponent.create().inject(this)

        workersUpdateViewModel = ViewModelProvider(this).get(WorkersUpdateViewModel::class.java)

        updateCachedData()

        replaceFragment(WorkersFragment())
    }

    private fun updateCachedData() {
        if (isOnline(applicationContext).first)
            workersUpdateViewModel.updateCachedData()
    }
}