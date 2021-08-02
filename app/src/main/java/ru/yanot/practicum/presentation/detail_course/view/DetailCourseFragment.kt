package ru.yanot.practicum.presentation.detail_course.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DetailCourseFragmentBinding
import ru.yanot.practicum.domain.Step
import ru.yanot.practicum.domain.StepState
import ru.yanot.practicum.domain.Topic
import ru.yanot.practicum.presentation.customview.topic.TopicList

class DetailCourseFragment : Fragment(R.layout.detail_course_fragment) {
    private val binding by viewBinding(DetailCourseFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topics = List(10) { index ->
            Topic(
                id = index.toLong(),
                description = "Super Topic#${index + 1}",
                available = index < 2,
                steps = listOf(
                    Step(
                        id = 1,
                        title = "Topic#${index + 1} Lesson1",
                        state = when (index) {
                            0 -> StepState.PASSED
                            1 -> StepState.PASSED
                            else -> StepState.CLOSE
                        }
                    ),
                    Step(
                        id = 2,
                        title = "Topic#${index + 1} Lesson2",
                        state = when (index) {
                            0 -> StepState.PASSED
                            1 -> StepState.PASSED
                            else -> StepState.CLOSE
                        }
                    ),
                    Step(
                        id = 3,
                        title = "Topic#${index + 1} Lesson3",
                        state =when (index) {
                            0 -> StepState.PASSED
                            1 -> StepState.IN_PROGRESS
                            else -> StepState.CLOSE
                        }
                    ),
                    Step(
                        id = 4,
                        title = "Topic#${index + 1} Lesson4",
                        state = when (index) {
                            0 -> StepState.PASSED
                            1 -> StepState.CLOSE
                            else -> StepState.CLOSE
                        }
                    ),
                    Step(
                        id = 5,
                        title = "Topic#${index + 1} Lesson5",
                        state = when (index) {
                            0 -> StepState.PASSED
                            1 -> StepState.CLOSE
                            else -> StepState.CLOSE
                        }
                    ),
                )
            )
        }


        binding.topcs.setContent {
            TopicList(topics)
        }
    }
}