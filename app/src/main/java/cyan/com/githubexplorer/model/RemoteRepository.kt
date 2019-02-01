package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.model.data.Contributor
import cyan.com.githubexplorer.model.data.Repo
import io.reactivex.Single

class RemoteRepository(
    private val apiService: GithubApi,
    private val executorService: ExecutorService
) : Repository {
    override fun fetchUserRepo(user: String, repo: String): Single<List<Repo>> = apiService
        .getUserRepo(user, repo)
        .compose(executorService.singleThreadExecutor())

    override fun fetchRepoContributors(user: String, repo: String): Single<List<Contributor>> = apiService
        .getRepoContributors(user, repo)
        .compose(executorService.singleThreadExecutor())
}