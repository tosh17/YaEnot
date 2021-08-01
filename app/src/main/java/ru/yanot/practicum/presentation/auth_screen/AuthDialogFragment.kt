package ru.yanot.practicum.presentation.auth_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentAuthBinding
import ru.yanot.practicum.utils.getColor
import ru.yanot.practicum.utils.getString
import ru.yanot.practicum.utils.loadText

class AuthDialogFragment : DialogFragment() {

    private val binding: DialogFragmentAuthBinding by viewBinding(DialogFragmentAuthBinding::bind)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.dialog_fragment_auth, container, false)
        with(dialog?.window) {
            this?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highlightText()
        val button = view.findViewById<MaterialButton>(R.id.log_in_btn)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_authDialogFragment_to_profileFragment)
        }
    }

    private fun highlightText() {
        val haveNotAccStr = R.string.you_havent_acc_str.getString(requireContext())
        val signInStr = R.string.sign_in.getString(requireContext())

        with(binding) {
            signInTextView.text = String.format(haveNotAccStr, signInStr)
            signInTextView.loadText(
                from = String.format(haveNotAccStr, signInStr)
                    .indexOf(signInStr),
                to = String.format(haveNotAccStr, signInStr)
                    .indexOf(signInStr)
                        + String.format(signInStr).length,
                colorInt = R.color.primary_default.getColor(requireContext()),
                click = {
                    //TODO add navigation
                }
            )
        }
    }
}