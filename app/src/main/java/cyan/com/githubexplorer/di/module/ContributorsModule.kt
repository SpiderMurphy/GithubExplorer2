package cyan.com.githubexplorer.di.module

import cyan.com.githubexplorer.contributors.mvi.ContributorsInteractor
import cyan.com.githubexplorer.contributors.mvi.ContributorsInteractorImpl
import cyan.com.githubexplorer.contributors.mvi.ContributorsMviPresenter
import cyan.com.githubexplorer.model.ExecutorServiceAsync
import cyan.com.githubexplorer.model.Repository
import dagger.Module
import dagger.Provides

@Module
class ContributorsModule {
    @Provides
    fun provideInteractor(repository: Repository): ContributorsInteractor =
        ContributorsInteractorImpl(repository, ExecutorServiceAsync())

    @Provides
    fun providePresenter(interactor: ContributorsInteractor): ContributorsMviPresenter =
        ContributorsMviPresenter(interactor)
}