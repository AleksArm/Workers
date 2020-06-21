package com.highestaim.workers.util


import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.TransitionDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import com.google.android.material.textfield.TextInputLayout


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


fun View.isHidden() = this.visibility == View.GONE || this.visibility == View.INVISIBLE
fun View.isVisible() = this.visibility == View.VISIBLE
fun View.isInvisible() = this.visibility == View.INVISIBLE
fun View.showIf(condition: Boolean) = if (condition) show() else hide()
fun View.hideIf(condition: Boolean) = if (condition) hide() else show()

inline fun <T : View> T.onClick(crossinline func: T.() -> Unit) {
    setOnClickListener { func() }
}

fun View.changeBackgroundColor(@ColorInt newColor: Int, duration: Int = 300) {
    val oldBackground = background
    val color = ColorDrawable(newColor)
    val ld = LayerDrawable(arrayOf<Drawable>(color))
    if (oldBackground == null) background = ld
    else {
        val td = TransitionDrawable(arrayOf(oldBackground, ld))
        background = td
        td.startTransition(duration)
    }
}


// force to show keyboard
fun <T : EditText> T.showKeyboard() {
    this.requestFocus()
    this.post {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}


fun Activity?.forceCloseKeyboard() {
    this?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

inline fun <T : EditText> T.onTextChanged(crossinline func: (orEmpty: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            func(s?.toString().orEmpty())
        }
    })
}
