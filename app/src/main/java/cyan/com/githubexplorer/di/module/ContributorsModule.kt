package cyan.com.githubexplorer.di.module

import cyan.com.githubexplorer.contributors.ContributorsFragment
import cyan.com.githubexplorer.contributors.ContributorsPresenter
import cyan.com.githubexplorer.contributors.ContributorsPresenterImpl
import cyan.com.githubexplorer.contributors.ContributorsView
import cyan.com.githubexplorer.model.Repository
import dagger.Module
import dagger.Provides

@Module
class ContributorsModule {
    @Provides
    fun provideView(view: ContributorsFragment): ContributorsView = view

    @Provides
    fun providePresenter(view: ContributorsView, repository: Repository): ContributorsPresenter =
        ContributorsPresenterImpl(view, repository)
}