package cyan.com.githubexplorer.di.module

import cyan.com.githubexplorer.graphics.MapSymbol
import cyan.com.githubexplorer.graphics.MapSymbolImpl
import cyan.com.githubexplorer.map.MapInteractor
import cyan.com.githubexplorer.map.MapInteractorImpl
import cyan.com.githubexplorer.map.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class MapModule {
    @Provides
    fun provideMapInteractor(): MapInteractor = MapInteractorImpl()

    @Provides
    fun provideMapPresenter(): MapPresenter = MapPresenter()

    @Provides
    fun provideMapSymbol(): MapSymbol = MapSymbolImpl()
}