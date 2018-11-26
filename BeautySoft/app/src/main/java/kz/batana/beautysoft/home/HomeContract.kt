package kz.batana.beautysoft.home

import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface HomeContract {

    interface PopularView: IView<PopularPresenter>{

    }

    interface PopularPresenter: IPresenter<PopularView>{

    }

    interface HomeRepository{

    }

    interface DiscountsView: IView<DiscountsPresenter>{

    }

    interface DiscountsPresenter: IPresenter<DiscountsView>{

    }

}