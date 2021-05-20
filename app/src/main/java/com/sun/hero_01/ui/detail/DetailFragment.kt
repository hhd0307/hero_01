package com.sun.hero_01.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.sun.hero_01.R
import com.sun.hero_01.base.BaseFragment
import com.sun.hero_01.data.model.HeroDetail
import com.sun.hero_01.data.model.HeroSpell
import com.sun.hero_01.data.source.HeroRepository
import com.sun.hero_01.data.source.remote.HeroRemoteDataSource
import com.sun.hero_01.utils.extensions.loadHeroClassImage
import com.sun.hero_01.utils.extensions.loadHeroSkinImage
import com.sun.hero_01.utils.extensions.loadHeroSquareImage
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment(), DetailContract.View {

    override val layoutResourceId = R.layout.fragment_detail
    override var bottomNavigationViewVisibility = View.GONE

    private var idHero: String? = null
    private var detailPresenter: DetailPresenter? = null
    private val heroAbilityAdapter by lazy {
        HeroAbilityAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            idHero = it.getString(ARGUMENT_HERO_ID).toString()
        }

        detailPresenter =
            DetailPresenter(HeroRepository.getInstance(HeroRemoteDataSource.getInstance()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailPresenter?.let {
            it.setView(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerHeroAbility.apply {
            setHasFixedSize(true)
            adapter = heroAbilityAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        detailPresenter?.let {
            idHero?.let { id -> it.getHeroDetail(id) }
        }
    }

    override fun loadHeroDetailOnSuccess(heroDetail: HeroDetail) {
        applyDataToView(heroDetail)
    }

    private fun applyDataToView(heroDetail: HeroDetail) {
        heroDetail.apply {
            textName.text = id
            textTitle.text = title
            textPrimaryTag.text = primaryTag
            textSecondaryTag.text = secondaryTag
            stats?.let {
                textHealNumber.text = it.hp.toString()
                textArmorNumber.text = it.armor.toString()
                textAttackNumber.text = it.attackDamage.toString()
                textMovementNumber.text = it.moveSpeed.toString()
                textRangeNumber.text = it.attackRange.toString()
                textAttackSpeedNumber.text = it.attackSpeed.toString()
                textHealRegenNumber.text = it.hpRegen.toString()
                textMagicResistNumber.text = it.spellBlock.toString()
            }
            imageHero.loadHeroSquareImage(image)
            imagePrimaryTag.loadHeroClassImage(primaryTag)
            imageSecondaryTag.loadHeroClassImage(secondaryTag)
            skins?.let {
                imageHeroBackground.loadHeroSkinImage("${this.id}_${it[0].num}.jpg")
                imageFirstSkin.loadHeroSkinImage("${this.id}_${it[1].num}.jpg")
                imageSecondSkin.loadHeroSkinImage("${this.id}_${it[2].num}.jpg")
            }
            heroAbilityAdapter.updateData(spells as MutableList<HeroSpell>?)
            textLoreDescription.text = lore
        }
    }

    override fun onError(exception: Exception?) {
        exception?.let {
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ARGUMENT_HERO_ID = "ARGUMENT_HERO_ID"

        fun newInstance(heroName: String?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_HERO_ID to heroName)
        }
    }
}
