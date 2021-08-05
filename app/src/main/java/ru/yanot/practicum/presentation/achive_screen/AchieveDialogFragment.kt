package ru.yanot.practicum.presentation.achive_screen

import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentAchieveBinding

class AchieveDialogFragment : DialogFragment(R.layout.dialog_fragment_achieve) {
    private val binding: DialogFragmentAchieveBinding by viewBinding(DialogFragmentAchieveBinding::bind)

    override fun getTheme(): Int {
        return R.style.DialogThemeCorner
    }
}