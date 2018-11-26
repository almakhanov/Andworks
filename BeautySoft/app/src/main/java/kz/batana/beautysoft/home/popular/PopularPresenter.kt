package kz.batana.beautysoft.home.popular

import kz.batana.beautysoft.core.util.BasePresenter
import kz.batana.beautysoft.home.HomeContract

class PopularPresenter(val repository: HomeContract.HomeRepository)
    :HomeContract.PopularPresenter, BasePresenter<HomeContract.PopularView>(){
    override fun viewIsReady() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}