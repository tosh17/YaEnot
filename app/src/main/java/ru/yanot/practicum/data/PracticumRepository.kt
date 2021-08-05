package ru.yanot.practicum.data

import ru.yanot.practicum.data.api.PracticumService
import javax.inject.Inject

class PracticumRepository @Inject constructor(
    private val remoteSource: PracticumService
) {



}