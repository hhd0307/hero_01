package com.sun.hero_01.ui.detail

import com.sun.hero_01.data.model.HeroDetail
import com.sun.hero_01.data.source.FavouriteRepository
import com.sun.hero_01.data.source.HeroRepository
import com.sun.hero_01.data.source.remote.OnFetchDataJsonListener
import com.sun.hero_01.utils.FakeData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.times

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailContract.View

    @Mock
    private lateinit var repository: HeroRepository

    @Mock
    private lateinit var favoriteRepository: FavouriteRepository

    @Mock
    private lateinit var exception: Exception

    private lateinit var presenter: DetailPresenter
    private val detail = FakeData.HERO_DETAIL

    @Before
    fun setUp() {
        presenter = DetailPresenter(repository, favoriteRepository)
        presenter.setView(view)
    }

    @Test
    fun get_hero_detail_success() {
        `when`(
            repository.getHeroDetails(
                ArgumentMatchers.anyString(),
                any()
            )
        ).thenAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<HeroDetail>).onSuccess(
                detail
            )
        }

        presenter.getHeroDetail(FakeData.ID_HERO)
        verify(view).loadHeroDetailOnSuccess(detail)
    }

    @Test
    fun get_hero_detail_error() {
        `when`(
            repository.getHeroDetails(
                ArgumentMatchers.anyString(),
                any()
            )
        ).thenAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<HeroDetail>).onError(
                exception
            )
        }

        presenter.getHeroDetail(FakeData.ID_HERO)
        verify(view, times(0)).loadHeroDetailOnSuccess(detail)
        verify(view).onError(exception)
    }
}
