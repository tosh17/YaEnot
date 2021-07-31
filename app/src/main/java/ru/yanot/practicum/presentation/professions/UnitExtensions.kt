package ru.yanot.practicum.presentation.professions

import android.content.res.Resources

inline val Int.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

