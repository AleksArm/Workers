package com.highestaim.workers.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.highestaim.workers.R
import com.highestaim.workers.ui.adapter.WorkersAdapter
import com.highestaim.workers.util.initRecyclerView
import com.highestaim.workers.util.replaceFragment
import com.highestaim.workers.util.toast
import com.highestaim.workers.viewmodel.WorkersViewModel
import kotlinx.android.synthetic.main.workers_fragment.*

class WorkersFragment : BaseFragment() {

    private lateinit var workersViewModel: WorkersViewModel
    private val workersAdapter = WorkersAdapter()

    override fun getLayoutId() = R.layout.workers_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        workersViewModel = ViewModelProvider(this).get(WorkersViewModel::class.java)
        initRecyclerView()
        initData()
        setWorkerClickListener()

        disableToolbarBackButton()

        setToolbarTitle(getString(R.string.app_name))
    }

    private fun initData() {
        workersViewModel.getDataFromDb().observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                it.getOrNull()?.let { data ->
                    workersAdapter.submitList(data)
                } ?: context?.toast(R.string.something_went_wrong)
            } else {
                context?.toast(R.string.something_went_wrong)
            }
        })
    }

    private fun initRecyclerView() {
        workersRecyclerView?.initRecyclerView(context, workersAdapter)
    }

    private fun setWorkerClickListener() {
        workersAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("worker", it)
            val detailFragment = WorkersDetailFragment()
            detailFragment.arguments = bundle
            replaceFragment(detailFragment, true)
        }
    }
}