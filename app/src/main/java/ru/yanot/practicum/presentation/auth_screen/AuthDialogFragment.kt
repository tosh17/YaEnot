package ru.yanot.practicum.presentation.auth_screen

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentAuthBinding
import ru.yanot.practicum.utils.getColor
import ru.yanot.practicum.utils.getString
import ru.yanot.practicum.utils.loadText

class AuthDialogFragment : DialogFragment(R.layout.dialog_fragment_auth) {

    private val binding: DialogFragmentAuthBinding by viewBinding(DialogFragmentAuthBinding::bind)

    private val authViewModel: AuthDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highlightText()
        setOnClickListeners()
        lifecycleScope.launch {
            authViewModel.authStateflow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .onEach(::setAuth).launchIn(lifecycleScope)
        }
    }

    private fun setAuth(state: AuthState) {
        when (state) {
            AuthState.EMPTY -> Unit
            is AuthState.ERROR -> {
                Log.e(javaClass.simpleName, state.exception.stackTraceToString())
                Snackbar.make(binding.emailTextView, "Error auth!", Snackbar.LENGTH_SHORT).show()
                setEnabledView(true)
            }
            AuthState.LOADING -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                setEnabledView(false)
            }
            AuthState.SUCCESS -> {
                findNavController().navigate(R.id.action_authDialogFragment_to_profileFragment)
                setEnabledView(true)
            }
        }
    }

    private fun setEnabledView(isEnabled: Boolean) {
        with(binding) {
            emailInputLayout.isEnabled = isEnabled
            passwordInputLayout.isEnabled = isEnabled
            logInBtn.isEnabled = isEnabled
            forgetPasswordTextView.isEnabled = isEnabled
            closeDialogBtn.isEnabled = isEnabled
            googleIcon.isEnabled = isEnabled
            facebookIcon.isEnabled = isEnabled
            vkIcon.isEnabled = isEnabled
            signInTextView.isEnabled = isEnabled
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

            emailEditText.addTextChangedListener {
                validateEmail()
            }

            passwordEditText.addTextChangedListener {
                validatePassword()
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

    override fun getTheme(): Int {
        return R.style.DialogThemeCorner
    }
}