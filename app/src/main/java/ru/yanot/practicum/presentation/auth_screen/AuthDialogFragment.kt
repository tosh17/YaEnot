package ru.yanot.practicum.presentation.auth_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentAuthBinding
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
                if (isValidate()) {
                    authViewModel.setAuthState(AuthState.LOADING)
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
            emailEditText.addTextChangedListener(TextFieldValidation(emailEditText))
            passwordEditText.addTextChangedListener(TextFieldValidation(passwordEditText))
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

    private fun isValidate(): Boolean =
        validateEmail() && validatePassword()

    private fun validateEmail(): Boolean {
        with(binding) {
            if (emailEditText.text.toString().trim().isEmpty()) {
                emailInputLayout.error = "Required Field!"
                emailEditText.requestFocus()
                return false
            } else if (!isValidEmail(emailEditText.text.toString())) {
                emailInputLayout.error = "Invalid Email!"
                emailEditText.requestFocus()
                return false
            } else {
                emailInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    /**
     * 1) field must not be empty
     * 2) password lenght must not be less than 6
     * 3) password must contain at least one digit
     * 4) password must contain atleast one upper and one lower case letter
     * 5) password must contain atleast one special character.
     */
    private fun validatePassword(): Boolean {
        with(binding) {
            if (passwordEditText.text.toString().trim().isEmpty()) {
                passwordInputLayout.error = "Required Field!"
                passwordEditText.requestFocus()
                return false
            } else {
                passwordInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.email_edit_text -> {
                    validateEmail()
                }
                R.id.password_edit_text -> {
                    validatePassword()
                }
            }
        }
    }
}