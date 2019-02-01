package cyan.com.githubexplorer.di

import cyan.com.githubexplorer.contributors.ContributorsFragment
import cyan.com.githubexplorer.di.module.ContributorsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [ContributorsModule::class])
    abstract fun bindContributorsView(): ContributorsFragment
}