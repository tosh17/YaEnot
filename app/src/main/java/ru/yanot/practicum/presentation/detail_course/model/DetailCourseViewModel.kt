package ru.yanot.practicum.presentation.detail_course.model

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.yanot.practicum.base.BaseViewModel
import ru.yanot.practicum.domain.Step
import ru.yanot.practicum.domain.StepState
import ru.yanot.practicum.domain.Topic
import javax.inject.Inject

@HiltViewModel
class DetailCourseViewModel @Inject constructor() : BaseViewModel() {

    //пока заглушка  есть моменты которые надо обсудить
    fun getTopics(): List<Topic> {
        return List(10) { index ->
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
                        state = when (index) {
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
    }
}