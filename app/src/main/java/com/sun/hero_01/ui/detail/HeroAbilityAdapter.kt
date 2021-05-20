package com.sun.hero_01.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.hero_01.R
import com.sun.hero_01.data.model.HeroSpell
import com.sun.hero_01.utils.extensions.loadHeroSpellImage
import kotlinx.android.synthetic.main.item_hero_ability.view.*

class HeroAbilityAdapter : RecyclerView.Adapter<HeroAbilityAdapter.ViewHolder?>() {

    private val spells = mutableListOf<HeroSpell>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero_ability, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = spells.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(spells[position])

    fun updateData(spells: MutableList<HeroSpell>?) {
        spells?.let {
            this.spells.clear()
            this.spells.addAll(it)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(spell: HeroSpell) {
            with(itemView) {
                imageHeroSpell.loadHeroSpellImage(spell.image)
                textSpellTitle.text = spell.name
                textSpellDetail.text = spell.description
                textSpellName.text = spell.id?.replace("Aatrox", "")
            }
        }
    }
}
