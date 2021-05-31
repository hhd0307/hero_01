package com.sun.hero_01.utils

import com.sun.hero_01.data.model.HeroDetail

object FakeData {
    const val  ID_HERO = "Name"

    val HERO_DETAIL = HeroDetail(
        id = "Hero",
        name = "Hero Name",
        title = "Title",
        skins = mutableListOf(),
        passive = null,
        primaryTag = "Primary Tag",
        secondaryTag = "Secondary Tag",
        image = "Image",
        info = null,
        lore = "Lore",
        spells = mutableListOf(),
        stats = null,
        isFavorite = false
    )
}
