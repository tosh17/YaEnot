package ru.yanot.practicum.presentation.customview.topic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.yanot.practicum.domain.StepState
import ru.yanot.practicum.domain.Topic
import ru.yanot.practicum.presentation.detail_course.view.compose.TopicComposeView

@Composable
fun TopicList(topics: List<Topic>) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        topics.forEach { topic ->
            TopicComposeView(
                topicTitle = topic.description,
                lessons = topic.steps.map { step -> step.title },
                //todo Надо подумать над структурой данных
                currentPosition = if (!topic.available) null
                else topic.steps.count { step -> step.state == StepState.PASSED },
                available = topic.available,
                isExpanded = topic.steps.any { step -> step.state == StepState.IN_PROGRESS }
            )
        }
    }
}