package ru.yanot.practicum.presentation.professions.ui

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.yanot.practicum.R
import ru.yanot.practicum.databinding.ItemProfessionCardBinding
import ru.yanot.practicum.utils.DiffCallbackImpl
import ru.yanot.practicum.presentation.professions.model.Profession
import ru.yanot.practicum.utils.dp

class ProfessionsAdapter : RecyclerView.Adapter<ProfessionsAdapter.ViewHolder>() {

    private var items: List<Profession> = listOf()

    fun setItems(new: List<Profession>) {
        val callback = DiffCallbackImpl(items, new)
        items = new
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_profession_card, parent, false)

        val binding = ItemProfessionCardBinding.bind(view)
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

    class ViewHolder(val binding: ItemProfessionCardBinding) : RecyclerView.ViewHolder(binding.root)

    class FirstItemOffsetDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
            if (itemPosition == 0) outRect.top = 20.dp.toInt()
            outRect.bottom = 12.dp.toInt()
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            if (parent.getChildAdapterPosition(view) == 0) outRect.top = 20.dp.toInt()
            outRect.bottom = 12.dp.toInt()
        }
    }
}