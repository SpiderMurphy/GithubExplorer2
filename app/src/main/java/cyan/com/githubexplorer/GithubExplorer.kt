package cyan.com.githubexplorer

import cyan.com.githubexplorer.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GithubExplorer : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out GithubExplorer> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}