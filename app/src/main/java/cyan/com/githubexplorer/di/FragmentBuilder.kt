package cyan.com.githubexplorer.di

import cyan.com.githubexplorer.contributors.ContributorsFragment
import cyan.com.githubexplorer.di.module.ContributorsModule
import cyan.com.githubexplorer.di.module.MapModule
import cyan.com.githubexplorer.map.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [ContributorsModule::class])
    abstract fun bindContributorsView(): ContributorsFragment

    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun bindMapView(): MapFragment
}