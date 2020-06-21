package com.highestaim.workers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.highestaim.workers.ui.MainActivity

abstract class BaseFragment : Fragment() {

    private val toolbar : Toolbar by lazy { (activity as MainActivity).toolbar }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)

    abstract fun getLayoutId() : Int


    fun enableToolbarBackButton(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun disableToolbarBackButton(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setToolbarBackButtonClickListener() {
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    fun setToolbarTitle(title : String) {
        toolbar.title = title
    }

}