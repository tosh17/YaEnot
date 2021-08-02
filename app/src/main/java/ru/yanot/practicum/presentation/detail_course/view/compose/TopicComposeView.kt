package ru.yanot.practicum.presentation.detail_course.view.compose

import android.widget.ProgressBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yanot.practicum.R

@Composable
private fun getProgressBarSize() = 40.dp

@Composable
fun TopicComposeView(
    topicTitle: String,
    lessons: List<String>,
    currentPosition: Int?,
    available: Boolean,
    isExpanded: Boolean
) {

    var expanded by remember { mutableStateOf(isExpanded) }
    val progress: Float = if (currentPosition != null)
        (currentPosition  ).toFloat() / lessons.size
    else 0f

    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = if (expanded) {
            0.dp
        } else {
            3.dp
        }
    ) {
        Column(
            modifier = Modifier
                .background(
                    if (expanded) {
                        colorResource(id = R.color.secondary_light)
                    } else {
                        colorResource(id = R.color.white)
                    }
                )
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Head(
                topicTitle,
                progress,
                expanded,
                available
            ) { expanded = !expanded }
            if (expanded) {
                lessons.forEachIndexed { index, lesson ->
                    LessonItem(
                        text = "${index + 1}. $lesson",
                        lessonState = getLessonState(index, currentPosition)
                    )
                }
            }
        }
    }
}

@Composable
private fun Head(
    topic: String,
    progress: Float,
    isExpanded: Boolean,
    available: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!available) {
            Box(
                modifier = Modifier
                    .border(
                        width = 4.dp,
                        color = colorResource(id = R.color.gray_light),
                        shape = CircleShape
                    )
                    .size(getProgressBarSize())
                    .clip(CircleShape),
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = "lock"
                )
            }
        } else {
            Progress(
                progress = progress,
                size = getProgressBarSize(),
                colorPrimary = colorResource(id = R.color.primary_default),
                colorSecondary = colorResource(id = R.color.primary_default).copy(alpha = 0.2f)
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = topic,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.W700,
            fontSize = 17.sp
        )
        Icon(
            modifier = Modifier
                .rotate(if (isExpanded) -90f else 90f)
                .clickable { onClick() }
                .padding(16.dp),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = "collapsed arrow"
        )
    }
}

@Composable
private fun LessonItem(text: String, lessonState: LessonState) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Bottom

    ) {
        LessonStateImage(lessonState)
        Text(
            modifier = Modifier
                .padding(start = 16.dp,bottom = 6.dp),
            text = text,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun LessonStateImage(state: LessonState) {
    Column(
        modifier = Modifier
            .width(getProgressBarSize()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(2.dp)
                .padding(bottom = 6.dp)
                .background(colorResource(id = R.color.primary_default).copy(alpha = 0.2f))
        )
        when (state) {
            LessonState.Passed -> Image(
                modifier = Modifier.padding(bottom = 6.dp),
                painter = painterResource(R.drawable.ic_round_check_mark),
                contentDescription = "print"
            )

            LessonState.InProgress -> Box(
                modifier = Modifier
                    .padding(bottom = 11.dp,top=5.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.additional_yellow))
            )
            LessonState.Closed -> Box(
                modifier = Modifier
                    .padding(bottom = 11.dp,top=5.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.primary_default).copy(alpha = 0.2f))
            )
        }
    }
}

@Composable
private fun Progress(
    progress: Float,
    size: Dp,
    colorPrimary: Color,
    colorSecondary: Color,
) {
    Canvas(
        modifier = Modifier
            .padding(start = 2.dp, top = 2.dp)
            .size(size)
    ) {
        val width = 4.dp.toPx()
        drawOval(
            colorSecondary,
            size = Size(size.toPx() - width / 2, size.toPx() - width / 2),
            style = Stroke(width = width)
        )
        drawArc(
            colorPrimary,
            270f,
            360 * progress,
            useCenter = false,
            size = Size(size.toPx() - width / 2, size.toPx() - width / 2),
            style = Stroke(width = width)
        )
    }

}

private fun getLessonState(position: Int, currenLessonPosition: Int?): LessonState {
    return when {
        currenLessonPosition == null -> LessonState.Closed
        position < currenLessonPosition -> LessonState.Passed
        position > currenLessonPosition -> LessonState.Closed
        else -> LessonState.InProgress
    }
}

private enum class LessonState {
    Passed, InProgress, Closed
}

@Preview
@Composable
private fun PreviewTopicComposeView() {
    TopicComposeView(
        topicTitle = "Super Topic",
        lessons = listOf(
            "lesson #1",
            "lessons #2",
            "lessons #23",
            "lessons #3",
            "lessons #5"
        ),
        currentPosition = 2,
        available = true,
        isExpanded = true
    )
}