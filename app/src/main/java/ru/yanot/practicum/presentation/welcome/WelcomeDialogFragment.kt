package ru.yanot.practicum.presentation.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.WelcomeDealogFragmentBinding
import ru.yanot.practicum.utils.getColor
import ru.yanot.practicum.utils.getString
import ru.yanot.practicum.utils.loadText

class WelcomeDialogFragment : DialogFragment(R.layout.welcome_dealog_fragment) {

    private val binding: WelcomeDealogFragmentBinding by viewBinding(WelcomeDealogFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        highlightText()

        initClickListener()
    }

    private fun initClickListener() {
        with(binding) {
            closeBtn.setOnClickListener {
                dismiss()
            }
            nextBtn.setOnClickListener { //TODO add navigation }
            }
        }
    }

    private fun highlightText() {
        val userAgreement = R.string.user_agreement.getString(requireContext())
        val userAgreementActionOne = R.string.user_agreement_action_one.getString(requireContext())
        val userAgreementActionTwo = R.string.user_agreement_action_two.getString(requireContext())

        with(binding) {
            agreementTextView.text =
                String.format(userAgreement, userAgreementActionOne, userAgreementActionTwo)
            agreementTextView.loadText(
                from = String.format(userAgreement, userAgreementActionOne, userAgreementActionTwo)
                    .indexOf(userAgreementActionOne),
                to = String.format(userAgreement, userAgreementActionOne, userAgreementActionTwo)
                    .indexOf(userAgreementActionOne)
                        + String.format(userAgreementActionOne).length,
                colorInt = R.color.primary_default.getColor(requireContext()),
                click = {
                    //TODO add navigation
                }
            )
            agreementTextView.loadText(
                from = String.format(userAgreement, userAgreementActionOne, userAgreementActionTwo)
                    .indexOf(userAgreementActionTwo),
                to = String.format(userAgreement, userAgreementActionOne, userAgreementActionTwo)
                    .indexOf(userAgreementActionTwo)
                        + String.format(userAgreementActionTwo).length,
                colorInt = R.color.primary_default.getColor(requireContext()),
                click = {
                    //TODO add navigation
                }
            )
        }
    }

    override fun getTheme(): Int {
        return R.style.WelcomeDialogTheme
    }
}