package cyan.com.githubexplorer.di.component

import android.app.Application
import cyan.com.githubexplorer.GithubExplorer
import cyan.com.githubexplorer.di.ActivityBuilder
import cyan.com.githubexplorer.di.module.ApplicationModule
import cyan.com.githubexplorer.di.module.NavRepositoryModule
import cyan.com.githubexplorer.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    RepositoryModule::class,
    NavRepositoryModule::class,
    ActivityBuilder::class
])
interface ApplicationComponent : AndroidInjector<GithubExplorer> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: GithubExplorer)
}