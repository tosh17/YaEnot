package ru.yanot.practicum.presentation.professions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfessionsListFragmentBinding

class ProfessionsListFragment : Fragment() {

    companion object {
        fun newInstance() = ProfessionsListFragment()
    }

    private val viewModel: ProfessionsViewModel by lazy {
        ViewModelProvider(this).get(ProfessionsViewModel::class.java)
    }

    private lateinit var binding: ProfessionsListFragmentBinding
    private lateinit var adapter: ProfessionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.professions_list_fragment, container, false)
        binding = ProfessionsListFragmentBinding.bind(view)
        setupAdapter()
        return view
    }

    private fun setupAdapter() {
        adapter = ProfessionsAdapter()
        adapter.setItems(viewModel.getData())

        binding.itemsView.adapter = adapter
    }

}