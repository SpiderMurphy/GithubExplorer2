package cyan.com.githubexplorer.di.module

import cyan.com.githubexplorer.model.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: GithubApi): Repository = RemoteRepository(api, ExecutorServiceAsync())
}