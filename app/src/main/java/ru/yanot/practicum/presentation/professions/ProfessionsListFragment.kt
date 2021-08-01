package ru.yanot.practicum.presentation.professions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfessionsListFragmentBinding

class ProfessionsListFragment : Fragment(R.layout.professions_list_fragment) {

    private val binding by viewBinding(ProfessionsListFragmentBinding::bind)

    companion object {
        fun newInstance() = ProfessionsListFragment()
    }

    private val viewModel: ProfessionsViewModel by lazy {
        ViewModelProvider(this).get(ProfessionsViewModel::class.java)
    }

    private lateinit var adapter: ProfessionsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        binding.appbar.addOnOffsetChangedListener(AppBarChangeListener(binding))
    }

    private fun setupAdapter() {
        adapter = ProfessionsAdapter()
        adapter.setItems(viewModel.getData())
        binding.itemsView.adapter = adapter
        binding.itemsView.addItemDecoration(ProfessionsAdapter.FirstItemOffsetDecoration(), 0)
    }
}