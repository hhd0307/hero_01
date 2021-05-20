package com.sun.hero_01.data.model

data class HeroDetail(
    val id: String?,
    val title: String?,
    val image: String?,
    val skins: List<HeroSkin>?,
    val lore: String?,
    val primaryTag: String?,
    val secondaryTag: String?,
    val info: HeroInfo?,
    val stats: HeroStat?,
    var spells: List<HeroSpell>?,
    val passive: HeroSpell?,
    var isFavorite: Boolean = false
)

data class HeroSpell(
    val id: String?,
    val name: String?,
    val description: String?,
    val image: String?
)

data class HeroStat(
    val hp: Int?,
    val moveSpeed: Int?,
    val armor: Int?,
    val attackRange: Int?,
    val hpRegen: Int?,
    val attackDamage: Int?,
    val attackSpeed: Float?,
    val spellBlock: Float?
)

data class HeroInfo(
    val attack: Int?,
    val defense: Int?,
    val magic: Int?,
    val difficulty: Int?
)

data class HeroSkin(
    val id: String?,
    val num: Int?,
    val name: String?
)

object HeroDetailEntry {
    const val DETAIL_OBJECT = "data"
    const val IMAGE_OBJECT = "image"
    const val SKIN_OBJECT = "skins"
    const val TAGS_OBJECT = "tags"
    const val INFO_OBJECT = "info"
    const val STATS_OBJECT = "stats"
    const val SPELLS_OBJECT = "spells"
    const val PASSIVE_OBJECT = "passive"
    const val ID = "id"
    const val TITLE = "title"
    const val IMAGE = "full"
    const val NAME = "name"
    const val DESCRIPTION = "description"
    const val SKIN_NUM = "num"
    const val LORE = "lore"
    const val INFO_ATTACK = "attack"
    const val INFO_DEFENSE = "defense"
    const val INFO_MAGIC = "magic"
    const val INFO_DIFFICULTY = "difficulty"
    const val STATS_HP = "hp"
    const val STATS_MOVESPEED = "hp"
    const val STATS_ARMOR = "armor"
    const val STATS_SPEELBLOCK = "spellblock"
    const val STATS_ATTACK_RANGE = "attackrange"
    const val STATS_HP_REGEN = "hpregen"
    const val STATS_ATTACK_DAME = "attackdamage"
    const val STATS_ATTACK_SPEED = "attackspeed"
}
