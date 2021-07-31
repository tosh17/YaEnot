package ru.yanot.practicum.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ProfileMenuItemBinding

class ProfileMenuAdapter(
    private val onClick:(ProfileMenuItem)->Unit)
    : ListAdapter<ProfileMenuItem, ProfileMenuHolder>(CheckListDiffUtil()) {
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
    RecyclerView.ViewHolder(binding.root){
    fun onBind(item: ProfileMenuItem?) {

    }

}

data class ProfileMenuItem(val text:String)

