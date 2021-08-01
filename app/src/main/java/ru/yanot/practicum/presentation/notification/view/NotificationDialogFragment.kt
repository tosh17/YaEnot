package ru.yanot.practicum.presentation.notification.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogNotificationFragmentBinding
import ru.yanot.practicum.presentation.notification.viewmodel.NotificationViewModel

@AndroidEntryPoint
class NotificationDialogFragment : DialogFragment(R.layout.dialog_notification_fragment) {

    private val viewModel: NotificationViewModel by viewModels()

    private lateinit var keyCourse: String

    private val binding: DialogNotificationFragmentBinding by viewBinding(
        DialogNotificationFragmentBinding::bind
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyCourse = arguments?.getString(COURSE_KEY) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()

        if (keyCourse.isNotEmpty()) viewModel.setKeyCourseNotification(keyCourse)
    }

    private fun initClickListeners() {
        with(binding) {

            closeBtn.setOnClickListener {
                dismiss()
            }

            notNowBtn.setOnClickListener {
                //TODO add navigation
            }

            turnOnNotificationBtn.setOnClickListener {
                viewModel.setKeyCourseNotification(keyCourse)
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.WelcomeDialogTheme
    }

    companion object {
        const val COURSE_KEY = "key_course"
    }
}