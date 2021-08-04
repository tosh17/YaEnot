package ru.yanot.practicum.presentation.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private val binding: ProfileFragmentBinding by viewBinding(ProfileFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.body.imageAvatar.load("https://proprikol.ru/wp-content/uploads/2019/09/prikolnye-foto-enotov-15.jpg") {
            crossfade(true)
            //  placeholder(R.drawable.image)
            transformations(CircleCropTransformation())
        }
        binding.body.userName.text = "I'm Enot, you are not"
        val adapter = ProfileMenuAdapter {
            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
        }
        adapter.submitList(
            listOf(
                ProfileMenuItem.Notification,
                ProfileMenuItem.Questions,
                ProfileMenuItem.Support,
                ProfileMenuItem.Payment,
                ProfileMenuItem.Exit,
            )
        )
        binding.body.recyclerView.adapter = adapter

    }
}