package ru.yanot.practicum.presentation.profile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfileMenuItemBinding

class ProfileMenuAdapter(
    private val onClick: (ProfileMenuItem) -> Unit
) : ListAdapter<ProfileMenuItem, ProfileMenuHolder>(CheckListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.profile_menu_item, parent, false)
        val binding: ProfileMenuItemBinding = ProfileMenuItemBinding.bind(view)
        return ProfileMenuHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ProfileMenuHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class CheckListDiffUtil : DiffUtil.ItemCallback<ProfileMenuItem>() {
    override fun areItemsTheSame(oldItem: ProfileMenuItem, newItem: ProfileMenuItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProfileMenuItem, newItem: ProfileMenuItem): Boolean {
        return oldItem == newItem
    }
}

class ProfileMenuHolder(
    private val binding: ProfileMenuItemBinding,
    private val onClick: (ProfileMenuItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ProfileMenuItem) {
        binding.icon.setImageResource(item.iconId)
        binding.text.setText(item.textId)
        binding.array.visibility = if(item.hasArrow) View.VISIBLE else View.INVISIBLE
        binding.separator.visibility = if(item.hasArrow) View.VISIBLE else View.INVISIBLE
        binding.root.setOnClickListener{onClick(item)}
    }

}

enum class ProfileMenuItem(val iconId: Int, val textId: Int, val hasArrow : Boolean = true) {
     Notification(
        R.drawable.ic_bell,
        R.string.prifile_menu_notification
    ),
     Questions(
        R.drawable.ic_menu_questions,
        R.string.prifile_menu_questions
    ),
     Support(
        R.drawable.ic_menu_support,
        R.string.prifile_menu_support
    ),
    Payment (
        R.drawable.ic_menu_payment,
        R.string.prifile_menu_payment
    ),
    Exit(
        R.drawable.ic_menu_exit,
        R.string.prifile_menu_exit,
        false
    )


}


