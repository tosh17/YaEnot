package ru.yanot.practicum.presentation.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.DialogFragmentPaymentBinding

class PaymentDialogFragment : DialogFragment(R.layout.dialog_fragment_payment) {

    private val binding by viewBinding(DialogFragmentPaymentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}