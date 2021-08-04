package ru.yanot.practicum.presentation.onboarding.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.OnboardingPageFragmentBinding

class OnboardingPageFragment : Fragment(R.layout.onboarding_page_fragment) {

    private val binding by viewBinding(OnboardingPageFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            arguments?.getString(ARGS_TITLE)?.let { title.text = it }
            arguments?.getString(ARGS_SUBTITLE)?.let { subtitle.text = it }
            arguments?.getInt(ARGS_IMAGE_RES)?.let { image.setImageResource(it) }
        }
    }

    companion object {
        private const val ARGS_IMAGE_RES = "args_image_res"
        private const val ARGS_TITLE = "args_title"
        private const val ARGS_SUBTITLE = "args_subtitle"

        fun newInstance(imageRes: Int?, title: String?, subtitle: String?): OnboardingPageFragment {
            return OnboardingPageFragment().apply {
                arguments = Bundle().apply {
                    imageRes?.let { putInt(ARGS_IMAGE_RES, imageRes) }
                    title?.let { putString(ARGS_TITLE, title) }
                    subtitle?.let { putString(ARGS_SUBTITLE, subtitle) }
                }
            }
        }
    }
}