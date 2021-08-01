package ru.yanot.practicum.presentation.auth_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.graphics.drawable.ColorDrawable

import android.graphics.Color
import android.view.Window
import ru.yanot.practicum.R


class AuthDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.dialog_fragment_auth, container, false)
        with(dialog?.window){
            this?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }
}