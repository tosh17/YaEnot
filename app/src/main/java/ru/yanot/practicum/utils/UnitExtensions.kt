package ru.yanot.practicum.utils

import android.content.res.Resources

inline val Int.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

