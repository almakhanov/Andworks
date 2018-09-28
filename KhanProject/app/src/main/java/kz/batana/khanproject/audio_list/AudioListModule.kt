package kz.batana.khanproject.audio_list

import org.koin.dsl.module.module

val audioListModule = module{
    factory { AudioListPresenter(get()) as AudioListContract.Presenter }
    factory { AudioListRepository(get()) as AudioListContract.Repository }
}