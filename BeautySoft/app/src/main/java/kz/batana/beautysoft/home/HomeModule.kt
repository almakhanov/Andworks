package kz.batana.beautysoft.home

import kz.batana.beautysoft.core.createService
import kz.batana.beautysoft.home.discounts.DiscountsPresenter
import kz.batana.beautysoft.home.popular.PopularPresenter
import org.koin.dsl.module.module

val homeModule = module {
    factory { PopularPresenter(get()) as HomeContract.PopularPresenter }
    factory { DiscountsPresenter(get()) as HomeContract.DiscountsPresenter }
    factory { InstitutionRepository(get(), get()) as HomeContract.HomeRepository }
    single { createService<HomeService>(get()) }
}