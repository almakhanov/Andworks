package kz.batana.beautysoft.home.discounts

import kz.batana.beautysoft.core.util.BasePresenter
import kz.batana.beautysoft.home.HomeContract

class DiscountsPresenter(val repository: HomeContract.HomeRepository)
    : HomeContract.DiscountsPresenter,  BasePresenter<HomeContract.DiscountsView>() {

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun viewIsReady() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}