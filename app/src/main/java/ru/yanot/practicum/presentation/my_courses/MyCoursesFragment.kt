package ru.yanot.practicum.presentation.my_courses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.MyCoursesFragmentBinding

@AndroidEntryPoint
class MyCoursesFragment : Fragment(R.layout.my_courses_fragment) {

    private val viewModel: MyCoursesViewModel by viewModels()
    private val binding by viewBinding(MyCoursesFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}