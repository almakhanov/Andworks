package kz.batana.beautysoft.core.util
interface IPresenter<V> {

    fun attachView(view: V)

    fun viewIsReady()

    fun detachView()

    fun destroy()
}