package cyan.com.githubexplorer.di.module

import cyan.com.githubexplorer.model.LocalNavRepoitory
import cyan.com.githubexplorer.model.NavRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavRepositoryModule {
    @Provides
    @Singleton
    fun provideNavRepository(): NavRepository = LocalNavRepoitory()
}