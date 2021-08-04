package ru.yanot.practicum.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt

fun TextView.loadText(from: Int, to: Int, @ColorInt colorInt: Int, click: View.OnClickListener) {
    if (from > to) return
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            click.onClick(widget)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }

    }
    val spannable = SpannableString(this.text).apply {
        setSpan(clickableSpan, from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(ForegroundColorSpan(colorInt), from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = spannable
}

fun Int.getString(context: Context): String = context.getString(this)

fun Int.getColor(context: Context): Int = context.getColor(this)