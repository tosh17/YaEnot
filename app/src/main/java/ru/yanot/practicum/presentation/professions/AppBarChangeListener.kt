package ru.yanot.practicum.presentation.professions

import com.google.android.material.appbar.AppBarLayout
import ru.yanot.practicum.databinding.ProfessionsListFragmentBinding
import kotlin.math.abs

class AppBarChangeListener(private val binding: ProfessionsListFragmentBinding) :
    AppBarLayout.OnOffsetChangedListener {


    override fun onOffsetChanged(appbar: AppBarLayout?, verticalOffset: Int) {
        appbar ?: return
        binding.searchContainer.background.alpha =
            (appbar.percentageOfHeight(verticalOffset) * 255).toInt()
    }

    private fun AppBarLayout.percentageOfHeight(offset: Int): Float =
        (1 - abs(offset).toFloat() / totalScrollRange)
}