package ru.yanot.practicum.presentation.professions

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ItemProfetionCardBinding

class ProfessionsAdapter : RecyclerView.Adapter<ProfessionsAdapter.ViewHolder>() {

    private var items: List<Profession> = listOf()

    fun setItems(new: List<Profession>) {
        val callback = DiffCallbackImpl(items, new)
        items = new
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_profetion_card, parent, false)

        val binding = ItemProfetionCardBinding.bind(view)
        return ViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            titleView.text = item.title
            // TODO: 31.07.2021 исправить хардкод
            timeInHoursView.text = "${item.durationHours} часов"
            timeInMonthView.text = "${item.durationMonth} месяцев"

            imageProfessionView.load(item.imageUrl) {
                transformations(RoundedCornersTransformation(8.dp))
                crossfade(true)
            }

        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val binding: ItemProfetionCardBinding) : RecyclerView.ViewHolder(binding.root)
}