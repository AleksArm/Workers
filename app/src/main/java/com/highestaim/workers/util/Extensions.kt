package com.highestaim.workers.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.workers.R


fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context.toast(@StringRes message: Int) = Toast.makeText(this, this.resources.getString(message), Toast.LENGTH_SHORT).show()

fun Fragment.replaceFragment(fragment: Fragment, addBackStack: Boolean) {
    if (addBackStack) {
        activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    } else {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, addBackStack: Boolean = false) {
    if (addBackStack) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commitAllowingStateLoss()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commitAllowingStateLoss()
    }
}

fun <R> RecyclerView.initRecyclerView(context: Context?, adapter: R, isVertical: Boolean = true) {
    context?.let {
        this.layoutManager = LinearLayoutManager(
            context,
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            false
        )
        this.adapter = adapter as RecyclerView.Adapter<*>
    }
}





