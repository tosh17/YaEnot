package ru.yanot.practicum.presentation.onboarding.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.OnboardingFragmentBinding
import ru.yanot.practicum.presentation.onboarding.adapter.OnboardingAdapter
import ru.yanot.practicum.presentation.onboarding.viewmodel.OnboardingViewModel
import ru.yanot.practicum.utils.ZoomOutPageTransformer

class OnboardingFragment : Fragment(R.layout.onboarding_fragment) {

    private var isLastPage: Boolean = false
    private var isSwipeStarted: Boolean = false

    private lateinit var viewModel: OnboardingViewModel

    private val binding by viewBinding(OnboardingFragmentBinding::bind)

    private val onboardingAdapter: OnboardingAdapter by lazy {
        val drawableResources = listOf(
            R.drawable.onb_one,
            R.drawable.onb_two,
            R.drawable.onb_three
        )

        val title = listOf(
            getString(R.string.onboarding_title_1),
            getString(R.string.onboarding_title_2),
            getString(R.string.onboarding_title_3)
        )

        val subTitle = listOf(
            getString(R.string.onboarding_subtitle_1),
            getString(R.string.onboarding_subtitle_2),
            getString(R.string.onboarding_subtitle_3)
        )
        OnboardingAdapter(this@OnboardingFragment, drawableResources, title, subTitle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        initClickListener()

    }

    private fun initClickListener(){
        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_professionsListFragment)
        }

        binding.selectCourseBtn.setOnClickListener {
           findNavController().navigate(R.id.action_onboardingFragment_to_authDialogFragment)
        }
    }

    private fun initViewPager(){
        with(binding) {
            viewPager.adapter = onboardingAdapter
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
            viewPager.setPageTransformer(ZoomOutPageTransformer())
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    when (state) {
                        ViewPager2.SCROLL_STATE_DRAGGING -> isSwipeStarted = true
                        ViewPager2.SCROLL_STATE_SETTLING -> isSwipeStarted = false
                        ViewPager2.SCROLL_STATE_IDLE -> if (isSwipeStarted && isLastPage) {
                            finishOnboarding()
                        }
                    }
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    isLastPage = position == onboardingAdapter.itemCount.minus(1)
                }
            })
        }
    }

/*    private fun onNextPressed() {
        val currentItem = binding.viewPager.currentItem
        if (onboardingAdapter.itemCount.compareTo(currentItem + 1) == 1) {
            binding.viewPager.currentItem = currentItem + 1
        } else {
            finishOnboarding()
        }
    }*/

    private fun finishOnboarding() {
       //viewModel.setOnboadingSeen()
        //findNavController().navigate(R.id.onboardingEdrugFragment)
    }

}