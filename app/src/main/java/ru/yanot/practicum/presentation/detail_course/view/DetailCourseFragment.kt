package ru.yanot.practicum.presentation.detail_course.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DetailCourseFragmentBinding
import ru.yanot.practicum.presentation.customview.topic.TopicList
import ru.yanot.practicum.presentation.detail_course.model.DetailCourseViewModel

class DetailCourseFragment : Fragment(R.layout.detail_course_fragment) {
    private val binding by viewBinding(DetailCourseFragmentBinding::bind)
    private val viewModel: DetailCourseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topics = viewModel.getTopics()
        binding.topcs.setContent {
            TopicList(topics)
        }
    }
}