package com.sun.hero_01.utils.extensions

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sun.hero_01.R
import com.sun.hero_01.utils.Constant
import com.sun.hero_01.utils.HeroClass
import com.sun.hero_01.utils.LoadImageBitmap

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun ImageView.loadHeroSquareImage(imageName: String?) {
    val url = "${Constant.BASE_URL}/${Constant.BASE_VERSION}/${Constant.PATH_IMAGE_CHAMPION}/"
    imageName?.let {
        LoadImageBitmap(this).execute(url + it)
    }
}

fun ImageView.loadHeroSkinImage(imageName: String?) {
    val url = "${Constant.BASE_URL}/${Constant.PATH_IMAGE_SKIN}/"
    imageName?.let {
        LoadImageBitmap(this).execute(url + it)
    }
}

fun ImageView.loadHeroSpellImage(imageName: String?) {
    val url = "${Constant.BASE_URL}/${Constant.BASE_VERSION}/${Constant.PATH_IMAGE_SKILL}/"
    imageName?.let {
        LoadImageBitmap(this).execute(url + it)
    }
}

fun ImageView.loadHeroPassiveImage(imageName: String?) {
    val url = "${Constant.BASE_URL}/${Constant.BASE_VERSION}/${Constant.PATH_IMAGE_PASSIVE}/"
    imageName?.let {
        LoadImageBitmap(this).execute(url + it)
    }
}

fun ImageView.loadHeroClassImage(tag: String?) {
    var iconClass: Int = R.drawable.ic_all_class
    tag?.let {
        when (tag) {
            HeroClass.ASSASSIN -> iconClass = R.drawable.ic_assassin
            HeroClass.FIGHTER -> iconClass = R.drawable.ic_fighter
            HeroClass.MAGE -> iconClass = R.drawable.ic_mage
            HeroClass.MARKSMAN -> iconClass = R.drawable.ic_marksman
            HeroClass.SUPPORT -> iconClass = R.drawable.ic_support
            HeroClass.TANK -> iconClass = R.drawable.ic_tank
        }
        this.setImageResource(iconClass)
    }
}
