package ru.yanot.practicum.presentation.profile.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfileFragmentBinding
import ru.yanot.practicum.presentation.profile.viewmodel.ProfileViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private val binding: ProfileFragmentBinding by viewBinding(ProfileFragmentBinding::bind)
    private val viewModel: ProfileViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.avatarUrl
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach(::setAvatarUrl)
            .launchIn(lifecycleScope)
        viewModel.userName
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { name ->
                binding.body.userName.text = name ?: ""
            }
            .launchIn(lifecycleScope)
        binding.fab.setOnClickListener { toast("fab") }
        val adapter = ProfileMenuAdapter(::onClickMenuItem)
        adapter.submitList(ProfileMenuItem.values().toList())
        binding.body.recyclerView.adapter = adapter
    }

    private fun setAvatarUrl(url: String?) {
        if (url == null) {
            binding.body.imageAvatar.setImageResource(R.drawable.ic_user)
        } else {
            binding.body.imageAvatar.load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_user)
                transformations(CircleCropTransformation())

            }
        }
    }

    private fun onClickMenuItem(menuItem: ProfileMenuItem) {
        toast(getString(menuItem.textId))
    }

    private fun toast(text: String) {
        Toast.makeText(
            context,
            "onClick $text",
            Toast.LENGTH_LONG
        ).show()
    }
}