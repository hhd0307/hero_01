package com.sun.hero_01.ui.detail

import com.sun.hero_01.data.model.HeroDetail
import com.sun.hero_01.data.model.HeroSpell
import com.sun.hero_01.data.source.remote.OnFetchDataJsonListener
import com.sun.hero_01.data.source.HeroRepository
import com.sun.hero_01.utils.HeroSpellSymbol

class DetailPresenter(private val repository: HeroRepository?) : DetailContract.Presenter {

    private var view: DetailContract.View? = null

    override fun getHeroDetail(id: String) {
        repository?.getHeroDetails(id, object :
            OnFetchDataJsonListener<HeroDetail> {
            override fun onSuccess(data: HeroDetail) {
                changeSpellNameToSymbol(data)
                view?.loadHeroDetailOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    private fun changeSpellNameToSymbol(data: HeroDetail) {
        val listSpells = mutableListOf<HeroSpell>()
        data.apply {
            passive?.let {
                listSpells.add(
                    HeroSpell(
                        HeroSpellSymbol.PASSIVE,
                        it.name,
                        it.description,
                        it.image
                    )
                )
            }
            spells?.let {
                for (item in it) {
                    listSpells.add(
                        HeroSpell(
                            item.id?.replace(data.id.toString(), ""),
                            item.name,
                            item.description,
                            item.image
                        )
                    )
                }
            }
        }
        data.apply { spells = listSpells }
    }

    override fun onStart() {
    }

    override fun onStop() {
        view = null
    }

    override fun setView(view: DetailContract.View?) {
        this.view = view
    }
}
