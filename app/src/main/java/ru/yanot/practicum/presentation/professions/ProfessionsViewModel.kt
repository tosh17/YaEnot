package ru.yanot.practicum.presentation.professions

import androidx.lifecycle.ViewModel

class ProfessionsViewModel : ViewModel() {


    fun getData() = mutableListOf<Profession>().apply {
        for (i in 0..15) add(
            Profession(
                id = 123123,
                title = "Web developer",
                durationMonth = 10,
                durationHours = 520,
                imageUrl = "https://i2.wp.com/whattonews.ru/wp-content/uploads/2020/04/desyat-aspektov-tehnologii-blokcheyn-kotorye-dolzhen-znat-kazhdyy-razrabotchik-1.jpg?fit=1024%2C683&ssl=1"
            )
        )
    }
}