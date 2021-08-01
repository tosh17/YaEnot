package ru.yanot.practicum.presentation.auth_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.razir.progressbutton.showProgress
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentAuthBinding
import ru.yanot.practicum.presentation.onboarding.viewmodel.OnboardingViewModel
import ru.yanot.practicum.utils.getColor
import ru.yanot.practicum.utils.getString
import ru.yanot.practicum.utils.loadText


@AndroidEntryPoint
class AuthDialogFragment : DialogFragment() {

    private val binding: DialogFragmentAuthBinding by viewBinding(DialogFragmentAuthBinding::bind)

    private val authViewModel: AuthDialogViewModel by viewModels()

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
        setOnClickListeners()

        authViewModel.observeAuthState().observe(viewLifecycleOwner) {
            when (it) {
                AuthState.EMPTY -> Unit
                is AuthState.ERROR -> {
                    Log.e(javaClass.simpleName, it.exception.stackTraceToString())
                    binding.logInBtn.showProgress {
                        buttonTextRes = R.string.log_in
                    }
                }
                AuthState.LOADING -> binding.logInBtn.showProgress {
                    progressColor = Color.WHITE
                }

                AuthState.SUCCESS -> findNavController().navigate(R.id.action_authDialogFragment_to_profileFragment)
            }
        }
    }

    private fun setOnClickListeners() {
        with(binding) {
            logInBtn.setOnClickListener {
                if (emailInputLayout.editText?.text.toString() != "" && passwordInputLayout.editText?.text.toString() != "") {
                    authViewModel.setAuthState(AuthState.LOADING)
                } else if (emailInputLayout.editText?.text.toString() == "") {
                    emailInputLayout.error = getString(R.string.not_empty_field)
                } else if (passwordInputLayout.editText?.text.toString() == "") {
                    passwordInputLayout.error = getString(R.string.not_empty_field)
                }
            }

            closeDialogBtn.setOnClickListener {
                dismiss()
            }

            forgetPasswordTextView.setOnClickListener {
                Toast.makeText(context, "Click forget", Toast.LENGTH_LONG).show()
            }

            googleIcon.setOnClickListener {
                Toast.makeText(context, "Google Auth", Toast.LENGTH_LONG).show()
            }
            facebookIcon.setOnClickListener {
                Toast.makeText(context, "Facebook Auth", Toast.LENGTH_LONG).show()
            }
            vkIcon.setOnClickListener {
                Toast.makeText(context, "Vk Auth", Toast.LENGTH_LONG).show()
            }
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
                    Toast.makeText(context, "Зарегистрироваться!", Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}