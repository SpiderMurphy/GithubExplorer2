package cyan.com.githubexplorer.di

import cyan.com.githubexplorer.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [
        FragmentBuilder::class
    ])
    abstract fun bindMainView(): MainActivity
}