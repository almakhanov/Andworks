package kz.batana.beautysoft.core.util

/**
 * Common view interface for app
 */
interface IView<out P : IPresenter<*>> {
    val presenter: P
}