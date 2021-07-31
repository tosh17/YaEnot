package ru.yanot.practicum.presentation.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.yanot.practicum.presentation.onboarding.view.OnboardingPageFragment

class OnboardingAdapter(
    fa: Fragment,
    val imagesRes: List<Int>,
    val title: List<String>,
    val subTitle: List<String>
) : FragmentStateAdapter(fa) {

    override fun getItemCount() = imagesRes.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingPageFragment.newInstance(
            imageRes = imagesRes.getOrNull(position),
            title = title.getOrNull(position),
            subtitle = subTitle.getOrNull(position),
        )
    }
}